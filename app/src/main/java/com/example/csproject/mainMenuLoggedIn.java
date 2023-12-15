package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class mainMenuLoggedIn extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    FirebaseAuth mAuth;
    View listing1, listing2;
    ImageView notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_logged_in);
        mAuth = FirebaseAuth.getInstance();

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
    }

    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        /*
        switch (menuItem.getItemId()) {
            case R.id.item1:
                System.out.println("Item 1");
                return true;

            case R.id.item2:
                System.out.println("Item 2");
                return true;

            case R.id.item3:
                System.out.println("Item 3");
                return true;

            case R.id.item4:
                System.out.println("Item 4");
                return true;
            default:
                return false;

        }
        */

        System.out.println("Hello");
        return false;

    }
}