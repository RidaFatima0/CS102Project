package com.example.csproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class editListing extends AppCompatActivity {


    int listNumber = 0;
    private EditText price, description, condition, location;
    ImageButton confirmButton;
    ImageView applogo;
    ImageView uploadimage;
    String imageURL;
    Uri uri;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_listing);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        price = findViewById(R.id.priceFill);
        description = findViewById(R.id.descriptionFill);
        condition = findViewById(R.id.conditionFill);
        location = findViewById(R.id.locationFill);
        uploadimage = findViewById(R.id.uploadimageButton);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    uri = data.getData();
                    uploadimage.setImageURI(uri);
                }
                else{
                    Toast.makeText(editListing.this, "No image selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
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

        confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String listId = "user" + listNumber;
                listNumber++;

                Listing listing = new Listing(condition.getText().toString(),
                        description.getText().toString(),
                        location.getText().toString(), listId,
                        Integer.parseInt(price.getText().toString()));

                mDatabase.child("Listing").child(listId).setValue(listing);

                Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
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