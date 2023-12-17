package com.example.csproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class signUp extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword, editfirstName, editLastName, editPhoneNumber, editConfirmPassword;
    Button buttonSignup;
    ImageView uploadImage;
    String imageURL;
    Uri uri;
    FirebaseAuth mAuth;
    TextView logininstead;
    ImageView applogo;
    private DatabaseReference mDatabase;


//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
//            startActivity(intent);
//            finish();
//        }
//    }

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        editTextEmail = findViewById(R.id.emailtextbox);
        editTextPassword = findViewById(R.id.passwordtextbox);
        editfirstName = findViewById(R.id.firstNametextbox);
        editLastName = findViewById(R.id.lastNametextbox);
        editPhoneNumber = findViewById(R.id.phoneNumbertextbox);
        editConfirmPassword = findViewById(R.id.confirmPasswordtextbox);
        uploadImage = findViewById(R.id.uploadimageButton);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    uri = data.getData();
                    uploadImage.setImageURI(uri);
                    imageURL = uri.toString();
                } else {
                    Toast.makeText(signUp.this, "No image selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(getApplicationContext(), mainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        logininstead = findViewById(R.id.logininstead);
        logininstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignup = findViewById(R.id.signuppagesinupbutton);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password, firstName, lastName, phoneNumber;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                firstName = String.valueOf(editfirstName.getText());
                lastName = String.valueOf(editLastName.getText());
                phoneNumber = String.valueOf(editPhoneNumber.getText());

                if (!password.equals(String.valueOf(editConfirmPassword.getText()))) {
                    Toast.makeText(signUp.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String userId = mAuth.getCurrentUser().getUid();
                                    addUserToFirestore(userId, firstName, lastName, email, phoneNumber, imageURL);
                                } else {
                                    Toast.makeText(signUp.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

        private void addUserToFirestore(String userId, String firstName, String lastName, String email, String phoneNumber, String imageURL) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String, Object> user = new HashMap<>();
            user.put("firstName", firstName);
            user.put("lastName", lastName);
            user.put("email", email);
            user.put("phoneNumber", phoneNumber);
            user.put("imageURL", imageURL);

            db.collection("users").document(userId)
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(signUp.this, "Account Created Successfully.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(signUp.this, "Failed to create account.", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
//        buttonSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email, password, firstName, lastName, phoneNumber;
//                email = String.valueOf(editTextEmail.getText());
//                password = String.valueOf(editTextPassword.getText());
//                firstName = String.valueOf(editfirstName.getText());
//                lastName = String.valueOf(editLastName.getText());
//                phoneNumber = String.valueOf(editPhoneNumber.getText());
//
//                if (!password.equals(String.valueOf(editConfirmPassword.getText()))) {
//                    Toast.makeText(signUp.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                UserData user = new UserData(firstName, lastName, email, phoneNumber, password, imageURL);
//
//                mDatabase.child("User").child(firstName).setValue(user);
//
//                //saveData();
//
//                mAuth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(signUp.this, "Account created.", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
//                                    startActivity(intent);
//                                    finish();
//                                } else {
//                                    Toast.makeText(signUp.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//            }
//        });

    public void saveData(){
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
//                .child(uri.getLastPathSegment());
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(String.valueOf(System.currentTimeMillis()));


        AlertDialog.Builder builder = new AlertDialog.Builder(signUp.this);
        builder.setCancelable(false);
        //builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });


//        if (TextUtils.isEmpty(email)){
//            Toast.makeText(signUp.this, "Enter email", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(password)){
//            Toast.makeText(signUp.this, "Enter password", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(firstName)){
//            Toast.makeText(signUp.this, "Enter first name", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(lastName)){
//            Toast.makeText(signUp.this, "Enter last name", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(phoneNumber)){
//            Toast.makeText(signUp.this, "Enter phone number", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (editTextPassword.equals(editConfirmPassword)){
//            Toast.makeText(signUp.this, "Password are not the same.",
//                    Toast.LENGTH_SHORT).show();
//        }
    }

    public void uploadData(){
        String email, password, firstName, lastName, phoneNumber;
        email = String.valueOf(editTextEmail.getText());
        password = String.valueOf(editTextPassword.getText());
        firstName = String.valueOf(editfirstName.getText());
        lastName = String.valueOf(editLastName.getText());
        phoneNumber = String.valueOf(editPhoneNumber.getText());

        UserData user = new UserData(firstName, lastName, email, phoneNumber, password, imageURL);
        FirebaseDatabase.getInstance().getReference("User").child(firstName).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(signUp.this, "Saved", Toast.LENGTH_SHORT).show();
                    finish();

                    Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                    startActivity(intent);
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(signUp.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), mainMenu.class);
        startActivity(intent);
        finish();
    }
}