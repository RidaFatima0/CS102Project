package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdatedFavList extends AppCompatActivity {

    ImageView applogo, listing1, bookmark1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updated_fav_list);

        applogo = findViewById(R.id.applogo);
        applogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                startActivity(intent);
                finish();
            }
        });

        bookmark1 = findViewById(R.id.bookmark2);
        bookmark1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdatedFavList.this, "Listing removed from saved list.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), EmptySavedList.class);
                startActivity(intent);
                finish();
            }
        });


    }
}