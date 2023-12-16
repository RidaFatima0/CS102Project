package com.example.csproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class editProfile extends AppCompatActivity {

    ImageView applogo;
    TextView editProfilePicOption;
    EditText firstName, lastName, email, phoneNumber, password;
    ImageView profilepic;
    Button savebutton;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    public Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        firstName = findViewById(R.id.firstNametextbox);
        lastName = findViewById(R.id.lastNametextbox);
        email = findViewById(R.id.emailtextbox);
        phoneNumber = findViewById(R.id.phoneNumbertextbox);
        password = findViewById(R.id.passwordtextbox);



        applogo = findViewById(R.id.applogo);
        applogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                startActivity(intent);
                finish();
            }
        });

        savebutton = findViewById(R.id.savebutton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(editProfile.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                    }
                });
            }
//                final String getFirstName = firstName.getText().toString().trim();
//                final String getLastName = lastName.getText().toString().trim();
//                final String getEmail = email.getText().toString().trim();
//                final String getPhoneNumber = phoneNumber.getText().toString().trim();
//                final String getPassword = password.getText().toString().trim();
//
//                if (getFirstName.isEmpty() || getLastName.isEmpty() || getEmail.isEmpty() || getPhoneNumber.isEmpty() || getPassword.isEmpty()) {
//                    Toast.makeText(editProfile.this, "All fields are required", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                HashMap<String, Object> userData = new HashMap<>();
//                userData.put("first name", getFirstName);
//                userData.put("last name", getLastName);
//                userData.put("email", getEmail);
//                userData.put("phone number", getPhoneNumber);
//                userData.put("password", getPassword);
//
//                FirebaseFirestore.getInstance().collection("User").document("UserData")
//                        .set(userData)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(editProfile.this, "Data Saved Successfully.", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(editProfile.this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
        });

//        savebutton = findViewById(R.id.savebutton);
//        savebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String getFirstName = firstName.getText().toString();
//                String getLastName = lastName.getText().toString();
//                String getEmail = email.getText().toString();
//                String getPhoneNumber = phoneNumber.getText().toString();
//                String getPassword = password.getText().toString();
//
//                HashMap<String, Object> hashMap = new HashMap<>();
//                hashMap.put("first name", getFirstName);
//                hashMap.put("last name", getLastName);
//                hashMap.put("email", getEmail);
//                hashMap.put("phone number", getPhoneNumber);
//                hashMap.put("password", getPassword);
//
//                FirebaseFirestore.getInstance().collection("User").document("UserData").set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(editProfile.this, "Data Saved Successfully.", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(editProfile.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
////                Intent intent = new Intent(getApplicationContext(), profilePageTenant.class);
////                startActivity(intent);
////                finish();
//            }
//        });

        profilepic = findViewById(R.id.profilepic);
        editProfilePicOption = findViewById(R.id.editprofilepic);
        editProfilePicOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });
    }

    public void choosePicture(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data.getData() != null){
            imageUri = data.getData();
            profilepic.setImageURI(imageUri);
            uploadPicture();
        }
    }

    public void uploadPicture(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                Snackbar.make(findViewById(android.R.id.content), "Image uploaded.", Snackbar.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Failed to upload.", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.00 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                pd.setMessage("Percentage: " + (int) progressPercent  + "%");
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