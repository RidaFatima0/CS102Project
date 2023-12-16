package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class specificListingPageLoggedOut extends AppCompatActivity {

    TextView login;
    TextView loginbutton, calculate, resultoutput;
    EditText monthlyincometextbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_listing_page_logged_out);

        calculate = findViewById(R.id.calculate);
        int rent = 16000;
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monthlyincometextbox = findViewById(R.id.monthlyincometextbox);
                int monthlyincome = Integer.parseInt(String.valueOf(monthlyincometextbox.getText()));
                resultoutput = findViewById(R.id.resultoutput);
                if (monthlyincome*12 < rent){
                    resultoutput.setTextColor(Color.RED);
                    resultoutput.setText("You cannot afford to rent this land.");
                }
                else{
                    resultoutput.setTextColor(Color.rgb(87,160,0));
                    resultoutput.setText("You can afford to rent this land.");
                }
            }
        });
        login = findViewById(R.id.loginoptionbutton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}