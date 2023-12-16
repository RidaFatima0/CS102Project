package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class notifications extends AppCompatActivity {

    ImageView applogo;
    TextView notif1, notif2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        applogo = findViewById(R.id.applogo);
        applogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                startActivity(intent);
                finish();
            }
        });

        notif1 = findViewById(R.id.notification1);
        notif1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notif1.setBackgroundColor(Color.rgb(203,239,205));
                notif1.setTypeface(Typeface.DEFAULT);
            }
        });

        notif2 = findViewById(R.id.notification2);
        notif2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notif2.setBackgroundColor(Color.rgb(203,239,205));
                notif2.setTypeface(Typeface.DEFAULT);
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