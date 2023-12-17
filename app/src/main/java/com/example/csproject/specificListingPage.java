package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class specificListingPage extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    View calculate,  radiobutton1, radiobutton2, radiobutton3, radiobutton4;;
    EditText monthlyincometextbox;
    TextView resultoutput, renttextview, requestcontact, landownerName, description;
    ImageView applogo, notification, displayimage, profile;

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

        description = findViewById(R.id.description);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                description.setText("Description: Tenant must utilize the land for agriculture purposes. This will ensure that the land is used properly.");
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
                displayimage.setImageResource(R.drawable.image1);
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
                displayimage.setImageResource(R.drawable.image6);
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
                displayimage.setImageResource(R.drawable.image4);
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
                displayimage.setImageResource(R.drawable.image5);
            }
        });

        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(specificListingPage.this, profile);
                popup.setOnMenuItemClickListener(specificListingPage.this);
                popup.inflate(R.menu.popup_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getTitle().equals("\uD83C\uDFE0 Home")){
                            Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (menuItem.getTitle().equals("\uD83D\uDC64 Profile")){
                            Intent intent = new Intent(getApplicationContext(), profilePageSeller.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (menuItem.getTitle().equals("★ Saved")){
                            Intent intent = new Intent(getApplicationContext(), favList.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (menuItem.getTitle().equals("⍇ Log Out")){
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                            finish();
                        }
                        return false;
                    }
                });
                popup.show();
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

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }
}