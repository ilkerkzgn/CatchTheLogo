// İlker Kızgın - 15030411035 | BUS 300 - Intern Project | 2020/05
package com.example.catchthelogo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    Switch switch_normal, switch_hard, switch_easy;
    Button button_start;
    String difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        switch_normal = findViewById(R.id.switch_normal);
        switch_easy = findViewById(R.id.switch_easy);
        switch_hard = findViewById(R.id.switch_hard);
        button_start = findViewById(R.id.button_start);

        // EASY
        switch_easy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    difficulty = "easy";
                    switch_normal.setChecked(false);
                    switch_hard.setChecked(false);
                }else {


                }
            }
        });

        //normal
        switch_normal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    difficulty = "normal";
                    switch_easy.setChecked(false);
                    switch_hard.setChecked(false);
                }else {

                }
            }
        });

        //HARD
        switch_hard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    difficulty = "hard";
                    switch_normal.setChecked(false);
                    switch_easy.setChecked(false);

                }else {

                }
            }
        });

    }

    public void start(View view){

        if (TextUtils.isEmpty(difficulty)){
            Toast.makeText(getApplicationContext(), "Please select a difficulty.", Toast.LENGTH_LONG).show();

        }else {
            Intent startGame = new Intent(Home.this,MainActivity.class);
            startGame.putExtra("difficulty",difficulty);
            startActivity(startGame);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }




    }

}
