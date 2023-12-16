package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class profilePageSeller extends AppCompatActivity {

    TextView edit, editrented1, editrented2;
    ImageView applogo, newlisting;
    View listing1, listing2, rectanglerented;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page_seller);

        applogo = findViewById(R.id.applogo);
        applogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                startActivity(intent);
                finish();
            }
        });

        edit = findViewById(R.id.editbutton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), editProfile.class);
                startActivity(intent);
                finish();
            }
        });

        listing1 = findViewById(R.id.rectangle1);
        listing1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), specificListingPage.class);
                startActivity(intent);
                finish();
            }
        });

        listing2 = findViewById(R.id.rectangle2);
        listing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), specificListingPage2.class);
                startActivity(intent);
                finish();
            }
        });

        rectanglerented = findViewById(R.id.rectanglerented);
        rectanglerented.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), profilePageTenant.class);
                startActivity(intent);
                finish();
            }
        });

        editrented1 = findViewById(R.id.editrented1);
        editrented1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), createNewListing.class);
                startActivity(intent);
                finish();
            }
        });

        editrented2 = findViewById(R.id.editrented2);
        editrented2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), createNewListing.class);
                startActivity(intent);
                finish();
            }
        });

        newlisting = findViewById(R.id.profilepic);
        newlisting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), createNewListing.class);
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