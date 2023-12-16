package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class filter extends AppCompatActivity {

    ImageView closebutton;
    Button clear, applyFilter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        textView = findViewById(R.id.loginoptionbutton);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });

        closebutton = findViewById(R.id.closebutton);
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        clear = findViewById(R.id.clearbutton);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), filter.class);
                startActivity(intent);
                finish();
            }
        });

        applyFilter = findViewById(R.id.applyfilterbutton);
        applyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                startActivity(intent);
                finish();
            }
        });
    }
}