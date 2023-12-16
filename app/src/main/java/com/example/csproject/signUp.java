package com.example.csproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class signUp extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword, editfirstName, editLastName, editPhoneNumber, editConfirmPassword;
    Button buttonSignup;
    ImageView uploadImage;
    String imageURL;
    Uri uri;
    FirebaseAuth mAuth;
    TextView logininstead;
    ImageView applogo;

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
                if (result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    uri = data.getData();
                    uploadImage.setImageURI(uri);
                }
                else{
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

        buttonSignup = findViewById(R.id.signuppagesinupbutton);

        logininstead = findViewById(R.id.logininstead);
        logininstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                

                String email, password, firstName, lastName, phoneNumber;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                firstName = String.valueOf(editfirstName.getText());
                lastName = String.valueOf(editLastName.getText());
                phoneNumber = String.valueOf(editPhoneNumber.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(signUp.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(signUp.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(firstName)){
                    Toast.makeText(signUp.this, "Enter first name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(lastName)){
                    Toast.makeText(signUp.this, "Enter last name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)){
                    Toast.makeText(signUp.this, "Enter phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editTextPassword.equals(editConfirmPassword)){
                    Toast.makeText(signUp.this, "Password are not the same.",
                            Toast.LENGTH_SHORT).show();
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(signUp.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(signUp.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
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