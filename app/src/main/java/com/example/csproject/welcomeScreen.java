package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class welcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
//        Intent intent = new Intent(getApplicationContext(), login.class);
//        startActivity(intent);
//        finish();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i  = new Intent(welcomeScreen.this,login.class);
            }
        },3000);
    }
}