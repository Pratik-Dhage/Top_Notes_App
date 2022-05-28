package com.example.top_notes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
        //use Handler class

       Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {

               //Example of Anonymous Object
              // startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));

               // jump to MainActivity after 3 seconds
               Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
               startActivity(intent);
               finish();
           }
       },3000);  //After 3secs/3000 milliseconds splashscreen will jump to main activity
    }
}