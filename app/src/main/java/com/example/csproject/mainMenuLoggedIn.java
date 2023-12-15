package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class mainMenuLoggedIn extends AppCompatActivity {

    FirebaseAuth auth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_logged_in);
    }
}