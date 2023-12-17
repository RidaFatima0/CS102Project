package com.example.csproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class createNewListing extends AppCompatActivity {


    int listNumber = 0;
    private EditText pricetextbox, descriptiontextbox, conditiontextbox, locationtextbox;
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
        setContentView(R.layout.activity_create_new_listing);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        pricetextbox = findViewById(R.id.priceFill);
        descriptiontextbox = findViewById(R.id.descriptionFill);
        conditiontextbox = findViewById(R.id.conditionFill);
        locationtextbox = findViewById(R.id.locationFill);
        uploadimage = findViewById(R.id.uploadimageButton);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    uri = data.getData();
                    uploadimage.setImageURI(uri);
                    imageURL = uri.toString();
                }
                else{
                    Toast.makeText(createNewListing.this, "No image selected", Toast.LENGTH_SHORT).show();
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
                String price, desc, condition, location;
                price = String.valueOf(pricetextbox.getText());
                desc = String.valueOf(descriptiontextbox.getText());
                condition = String.valueOf(conditiontextbox.getText());
                location = String.valueOf(locationtextbox.getText());

                String listingid = mAuth.getCurrentUser().getUid();
                addListingtoFirestore(listingid, price, desc, condition, location, imageURL);


                Intent intent = new Intent(getApplicationContext(), profilepageSellerEdited.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void addListingtoFirestore(String listingid, String price, String desc, String condition, String location, String imageURL) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> listing = new HashMap<>();
        listing.put("price", price);
        listing.put("description", desc);
        listing.put("condition", condition);
        listing.put("location", location);
        listing.put("imageURL", imageURL);

        db.collection("listings").document(listingid)
                .set(listing)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(createNewListing.this, "New listing added successfully.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(createNewListing.this, "New Listing added.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), profilePageSeller.class);
        startActivity(intent);
        finish();
    }
}