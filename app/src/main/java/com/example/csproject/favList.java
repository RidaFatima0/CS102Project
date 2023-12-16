package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class favList extends AppCompatActivity {

    ImageView applogo, listing1, listing2, bookmark1, bookmark2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_list);


        applogo = findViewById(R.id.applogo);
        applogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                startActivity(intent);
                finish();
            }
        });

        listing1 = findViewById(R.id.image1);
        listing1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), specificListingPage.class);
                startActivity(intent);
                finish();
            }
        });

        bookmark1 = findViewById(R.id.bookmark1);
        bookmark1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(favList.this, "Listing removed from saved list.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), UpdatedFavList.class);
                startActivity(intent);
                finish();
            }
        });

        listing2 = findViewById(R.id.image1);
        listing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), specificListingPage2.class);
                startActivity(intent);
                finish();
            }
        });

//        bookmark2 = findViewById(R.id.bookmark2);
//        bookmark2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(favList.this, "Listing removed from saved list.", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }
}