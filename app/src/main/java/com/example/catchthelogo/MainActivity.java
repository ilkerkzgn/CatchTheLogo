// İlker Kızgın - 15030411035 | BUS 300 - Intern Project | 2020/05
package com.example.catchthelogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView_score, textView_time, textView_finish;
    int score, r;
    ImageView logo, logo2, logo3, logo4, logo5, logo6, logo7, logo8, logo9, logo10, logo11, logo12;
    ImageView[] imagearray;
    Handler handler;
    Runnable runnable;
    Random random;
    Button button_restart;
    String difficulty;


    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        logo2 = findViewById(R.id.logo2);
        logo3 = findViewById(R.id.logo3);
        logo4 = findViewById(R.id.logo4);
        logo5 = findViewById(R.id.logo5);
        logo6 = findViewById(R.id.logo6);
        logo7 = findViewById(R.id.logo7);
        logo8 = findViewById(R.id.logo8);
        logo9 = findViewById(R.id.logo9);
        logo10 = findViewById(R.id.logo10);
        logo11 = findViewById(R.id.logo11);
        logo12 = findViewById(R.id.logo12);

        imagearray = new ImageView[] {logo, logo2, logo3, logo4, logo5, logo6, logo7, logo8, logo9, logo10, logo11, logo12};



        difficulty = getIntent().getStringExtra("difficulty");
        textView_score = findViewById(R.id.textView_score);
        textView_time = findViewById(R.id.textView_time);

        textView_finish = findViewById(R.id.textView_finish);
        button_restart = findViewById(R.id.button_restart);

        textView_finish.setVisibility(View.INVISIBLE);
        button_restart.setVisibility(View.INVISIBLE);
        score = 0;

        hide();
        Timer();



    }


    public void hide() {


        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imagearray){
                    image.setVisibility(View.INVISIBLE);
                }
                random = new Random();
                r = random.nextInt(12);
                imagearray[r].setVisibility(View.VISIBLE);
                if (difficulty.equals("easy")){
                    handler.postDelayed(this,1000);

                }
                if (difficulty.equals("normal")){
                    handler.postDelayed(this,600);

                }
                if (difficulty.equals("hard")){

                    handler.postDelayed(this,300);
                }


            }
        };

        handler.post(runnable);

    }
    public void plusOne (View view){

        score++;
        textView_score.setText("Score : "+score);
        imagearray[r].setVisibility(View.INVISIBLE);


    }

    public void Timer () {

        new CountDownTimer(20000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textView_time.setText("Time : " +millisUntilFinished / 1000 +" second");
            }

            @Override
            public void onFinish() {

                textView_time.setText("Time Finished!");
                handler.removeCallbacks(runnable);
                textView_score.setVisibility(View.INVISIBLE);
                button_restart.setVisibility(View.VISIBLE);
                textView_finish.setVisibility(View.VISIBLE);

                for (ImageView image : imagearray){
                    image.setVisibility(View.INVISIBLE);
                }

                if (score >= 0 && score <= 14){
                    textView_finish.setText("Your score is :"+score+" Maybe next time");


                }else if (score >= 15 && score <= 49){
                    textView_finish.setText("Your score is :"+score+" Well done!");


                }else if (score >= 50){
                    textView_finish.setText("Your score is :"+score+" You're on fire!");
                }


            }
        }.start();
    }



    public void restart (View view){

        Intent intentToRestart = getIntent();
        finish();
        startActivity(intentToRestart);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void back (View view){
        Intent intent = new Intent(MainActivity.this,Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        finish();
    }

}
