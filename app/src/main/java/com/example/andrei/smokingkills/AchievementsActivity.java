package com.example.andrei.smokingkills;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class AchievementsActivity extends AppCompatActivity {

    static int nr=0;

/*    Boolean checkBoxState = d1.isChecked();
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);


        TextView textElement = (TextView) findViewById(R.id.textView);
        textElement.setText(Integer.toString(nr));
}
    public void itemClicked(View v) {
        if (((CheckBox) v).isChecked()) {
            nr=nr+1;
        }

    }
}
