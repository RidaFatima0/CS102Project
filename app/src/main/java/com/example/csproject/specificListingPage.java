package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class specificListingPage extends AppCompatActivity {

    View calculate;
    EditText monthlyincometextbox;
    TextView resultoutput, renttextview, requestcontact, landownerName;
    ImageView applogo, notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_listing_page);

        calculate = findViewById(R.id.calculatebutton);
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

        renttextview = findViewById(R.id.rentTextView);
        renttextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                renttextview.setText("Landowner will contact you.");
            }
        });
        requestcontact = findViewById(R.id.requestsellercontacttextview);
        requestcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestcontact.setText("Request sent to landownder.");
            }
        });
//
        landownerName = findViewById(R.id.landownerName);
        landownerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), viewSellerProfile.class);
                startActivity(intent);
                finish();
            }
        });

        applogo = findViewById(R.id.applogo);
        applogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                startActivity(intent);
                finish();
            }
        });

        notification = findViewById(R.id.notificationicon);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), notifications.class);
                startActivity(intent);
                finish();
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