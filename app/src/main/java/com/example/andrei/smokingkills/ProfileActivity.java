package com.example.andrei.smokingkills;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andrei.smokingkills.model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;

    public User currentUser;

    TextView nameText;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        currentUser = LoginActivity.currentUser;

        nameText = findViewById(R.id.profileName);
        img      = findViewById(R.id.profilePhoto);

        nameText.setText(currentUser.getName());

        File f = new File( getFilesDir(),currentUser.getName() + "_profile_picture.png");
        if(!f.exists())
            saveProfilePicture(BitmapFactory.decodeResource(getResources(), R.drawable.blank_profile_photo));

        refreshProfileImage();
    }

    public void onClickProfilePhoto(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                saveProfilePicture(bitmap);
                refreshProfileImage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void refreshProfileImage() {

        FileInputStream stream = null;
        try {
            Bitmap picture = BitmapFactory.decodeStream(openFileInput( currentUser.getName() + "_profile_picture.png"));
            img.setImageBitmap(picture);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveProfilePicture(Bitmap bitmap) {
        FileOutputStream out = null;
        try {
            File f = new File( getFilesDir(),currentUser.getName() + "_profile_picture.png");
            f.createNewFile();

            out = openFileOutput(currentUser.getName() + "_profile_picture.png", MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
