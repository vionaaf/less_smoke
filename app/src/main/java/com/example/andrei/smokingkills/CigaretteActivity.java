package com.example.andrei.smokingkills;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CigaretteActivity extends AppCompatActivity {
    ProgressBar prg;
    Button btn;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cigarette);
        prg =(ProgressBar)findViewById(R.id.progressBar);
        btn = (Button)findViewById(R.id.plus);
        txt = (TextView)findViewById(R.id.textView2);
        updateText();
    }

    public void Plus(View view) {
        prg.setProgress(prg.getProgress()+1);
        updateText();
    }
    public void Minus(View view){
        prg.setProgress(prg.getProgress()-1);
        updateText();
    }

    public void updateText( ) {txt.setText("You smoked " + Integer.toString(prg.getProgress()) + " cigarettes");}
    public void onClickProfile(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
    public void onClickAchievements(View view){
        Intent intent = new Intent(this, AchievementsActivity.class);
        startActivity(intent);
    }
    public void onClickProgress(View view){
        Intent intent = new Intent(this, GraphsActivity.class);
        startActivity(intent);
    }


}

