package com.example.andrei.smokingkills;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.andrei.smokingkills.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    public static ArrayList<User> userList;
    public static User currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.e("LoginActivity", "Application started!");

        try {
            FileInputStream fileInputStream =
                    openFileInput(RegisterActivity.usersFile);
            ObjectInputStream stream = new ObjectInputStream(fileInputStream);
            userList = (ArrayList<User>) stream.readObject();
            stream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }/* catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/ catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Button registerBtn = findViewById(R.id.registerButton);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        EditText userEditTxt =  findViewById(R.id.UserEditText1);
        EditText passEditTxt = findViewById(R.id.PasswordEditText1);
        userEditTxt.setText("");
        passEditTxt.setText("");
    }

    public void Login(View view) {
        EditText userEditTxt = findViewById(R.id.UserEditText1);
        EditText passEditTxt = findViewById(R.id.PasswordEditText1);

        if (userEditTxt.getText().toString().trim().length() <= 4 ||
                passEditTxt.getText().toString().trim().length() <= 4) {
            Toast.makeText(getApplicationContext(),
                    R.string.login_error,
                    Toast.LENGTH_LONG).show();
            return;
        }

        boolean userFound = false;
        if (userList != null) {

            for (User u : userList) {
                if (userEditTxt.getText().toString().equals(u.getName()) &&
                        passEditTxt.getText().toString().hashCode() == u.getPassword()) {
                    User user = new User(userEditTxt.getText().toString().trim(),
                            passEditTxt.getText().toString().trim());
                    LoginActivity.currentUser = user;
                    userFound = true;
                    break;
                }
            }
        }
        if (userFound) {
            Intent intent =
                    new Intent(LoginActivity.this, CigaretteActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),
                    R.string.incorrect_login,
                    Toast.LENGTH_LONG).show();
        }

    }
}