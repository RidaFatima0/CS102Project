package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class viewListing extends AppCompatActivity {

    ImageView displayimage, applogo;
    View radiobutton1, radiobutton2, radiobutton3, radiobutton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_listing);


        applogo = findViewById(R.id.applogo);
        applogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                startActivity(intent);
                finish();
            }
        });

        displayimage = findViewById(R.id.displayimage);
        radiobutton1 = findViewById(R.id.radiobutton1);
        radiobutton2 = findViewById(R.id.radiobutton2);
        radiobutton3 = findViewById(R.id.radiobutton3);
        radiobutton4 = findViewById(R.id.radiobutton4);

        radiobutton1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                radiobutton1.setBackgroundColor(Color.rgb(87,160,0));
                radiobutton2.setBackgroundColor(Color.BLACK);
                radiobutton3.setBackgroundColor(Color.BLACK);
                radiobutton4.setBackgroundColor(Color.BLACK);
                displayimage.setImageResource(R.drawable.image6);
            }
        });

        radiobutton2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                radiobutton2.setBackgroundColor(Color.rgb(87,160,0));
                radiobutton1.setBackgroundColor(Color.BLACK);
                radiobutton3.setBackgroundColor(Color.BLACK);
                radiobutton4.setBackgroundColor(Color.BLACK);
                displayimage.setImageResource(R.drawable.image5);
            }
        });

        radiobutton3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                radiobutton3.setBackgroundColor(Color.rgb(87,160,0));
                radiobutton1.setBackgroundColor(Color.BLACK);
                radiobutton2.setBackgroundColor(Color.BLACK);
                radiobutton4.setBackgroundColor(Color.BLACK);
                displayimage.setImageResource(R.drawable.image3);
            }
        });

        radiobutton4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                radiobutton4.setBackgroundColor(Color.rgb(87,160,0));
                radiobutton1.setBackgroundColor(Color.BLACK);
                radiobutton2.setBackgroundColor(Color.BLACK);
                radiobutton3.setBackgroundColor(Color.BLACK);
                displayimage.setImageResource(R.drawable.image4);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
        startActivity(intent);
        finish();
    }
}