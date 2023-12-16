package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class createNewListing extends AppCompatActivity {


    private DatabaseReference mDatabase;
    int listNumber = 0;
    private EditText priceFill, descriptionFill, conditionFill, locationFill;
    ImageView applogo;
    Button uploadimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_listing);

        priceFill = findViewById(R.id.priceFill);
        descriptionFill = findViewById(R.id.descriptionFill);
        conditionFill = findViewById(R.id.conditionFill);
        locationFill = findViewById(R.id.locationFill);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        applogo = findViewById(R.id.applogo);
        applogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public String createListId(){
        String listId = "user" + listNumber;
        listNumber++;
        return listId;
    }
    public void confirmListing(){
        writeNewList(conditionFill.getText().toString(),
                descriptionFill.getText().toString(),
                locationFill.getText().toString(),
                createListId(),
                Integer.parseInt(priceFill.getText().toString()));
        System.out.println("Oldu");
    }

    public void writeNewList(String condition, String description, String location, String listId, int rent) {
        Listing listing = new Listing(condition, description, location, listId, rent);

        mDatabase.child("listings").child(listId).setValue(listing);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
        startActivity(intent);
        finish();
    }
}