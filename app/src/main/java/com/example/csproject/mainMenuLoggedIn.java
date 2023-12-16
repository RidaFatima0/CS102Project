package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import com.google.firebase.auth.FirebaseAuth;

public class mainMenuLoggedIn extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    //FirebaseAuth mAuth;
    View listing1, listing2;
    ImageView notification, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_logged_in);
        //mAuth = FirebaseAuth.getInstance();

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

        notification = findViewById(R.id.notificationicon);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), notifications.class);
                startActivity(intent);
                finish();
            }
        });

        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(mainMenuLoggedIn.this, profile);
                popup.setOnMenuItemClickListener(mainMenuLoggedIn.this);
                popup.inflate(R.menu.popup_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getTitle().equals("\uD83C\uDFE0 Home")){
                            Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (menuItem.getTitle().equals("\uD83D\uDC64 Profile")){
                            Intent intent = new Intent(getApplicationContext(), profilePageSeller.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (menuItem.getTitle().equals("★ Saved")){
                            Intent intent = new Intent(getApplicationContext(), favList.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (menuItem.getTitle().equals("⍇ Log Out")){
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                            finish();
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }
}