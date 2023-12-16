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

//        uploadimage = findViewById(R.id.uploadimageButton);
//        uploadimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
////                    return;
//                } else {
//                    launchGalleryIntent();
//                }
//            }
//        });
//    }
//
//    public void launchGalleryIntent() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.setType("image/*");
//        startActivityForResult(intent, REQUEST_EXTERNAL_STORAGE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_EXTERNAL_STORAGE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//                    launchGalleryIntent();
//                } else {
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request.
//        }
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_EXTERNAL_STORAGE && resultCode == RESULT_OK) {
//
//            final ImageView imageView = findViewById(R.id.image_view);
//            final List<Bitmap> bitmaps = new ArrayList<>();
//            ClipData clipData = data.getClipData();
//
//            if (clipData != null) {
//                //multiple images selecetd
//                for (int i = 0; i < clipData.getItemCount(); i++) {
//                    Uri imageUri = clipData.getItemAt(i).getUri();
//                    Log.d("URI", imageUri.toString());
//                    try {
//                        InputStream inputStream = getContentResolver().openInputStream(imageUri);
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        bitmaps.add(bitmap);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } else {
//                //single image selected
//                Uri imageUri = data.getData();
//                Log.d("URI", imageUri.toString());
//                try {
//                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    bitmaps.add(bitmap);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (final Bitmap b : bitmaps) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                imageView.setImageBitmap(b);
//                            }
//                        });
//
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }).start();
//        }
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