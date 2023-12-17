package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainMenuSearchedLoggedIn extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ImageView applogo, searchicon, displayimage, filter, notification, profile;
    EditText search;
    View radiobutton1, radiobutton2, radiobutton3, radiobutton4;
    TextView imagetext1, imagetext2, imagetext3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_searched_logged_in);

        applogo = findViewById(R.id.applogo);
        applogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
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

        filter = findViewById(R.id.filtericon);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FilterLoggedIn.class);
                startActivity(intent);
                finish();
            }
        });


        displayimage = findViewById(R.id.displayimage);
        radiobutton1 = findViewById(R.id.radiobutton1);
        radiobutton2 = findViewById(R.id.radiobutton2);
        radiobutton3 = findViewById(R.id.radiobutton3);
        radiobutton4 = findViewById(R.id.radiobutton4);
        imagetext1 = findViewById(R.id.imagetext1);
        imagetext2 = findViewById(R.id.imagetext2);
        imagetext3 = findViewById(R.id.imagetext3);

        radiobutton1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                radiobutton1.setBackgroundColor(Color.rgb(87,160,0));
                radiobutton2.setBackgroundColor(Color.BLACK);
                radiobutton3.setBackgroundColor(Color.BLACK);
                radiobutton4.setBackgroundColor(Color.BLACK);
                displayimage.setImageResource(R.drawable.image2);
                imagetext1.setText("Your Planet");
                imagetext2.setText("Your Future");
                imagetext3.setText("Your Choice!");
            }
        });

        radiobutton2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                radiobutton2.setBackgroundColor(Color.rgb(87,160,0));
                radiobutton1.setBackgroundColor(Color.BLACK);
                radiobutton3.setBackgroundColor(Color.BLACK);
                radiobutton4.setBackgroundColor(Color.BLACK);
                displayimage.setImageResource(R.drawable.advert);
                imagetext1.setText("");
                imagetext2.setText("");
                imagetext3.setText("");
            }
        });

        radiobutton3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                radiobutton3.setBackgroundColor(Color.rgb(87,160,0));
                radiobutton1.setBackgroundColor(Color.BLACK);
                radiobutton2.setBackgroundColor(Color.BLACK);
                radiobutton4.setBackgroundColor(Color.BLACK);
                displayimage.setImageResource(R.drawable.advertisement2);
                imagetext1.setText("");
                imagetext2.setText("");
                imagetext3.setText("");
            }
        });

        radiobutton4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                radiobutton4.setBackgroundColor(Color.rgb(87,160,0));
                radiobutton1.setBackgroundColor(Color.BLACK);
                radiobutton2.setBackgroundColor(Color.BLACK);
                radiobutton3.setBackgroundColor(Color.BLACK);
                displayimage.setImageResource(R.drawable.advertisement3);
                imagetext1.setText("");
                imagetext2.setText("");
                imagetext3.setText("");
            }
        });


        searchicon = findViewById(R.id.searchicon);
        search = findViewById(R.id.search);
        searchicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (search.getText().toString().equals("Konya")){
                    Intent intent = new Intent(getApplicationContext(), konyaSearchMainMenu.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainMenuSearchedLoggedIn.this, profile);
                popup.setOnMenuItemClickListener(MainMenuSearchedLoggedIn.this);
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
        startActivity(intent);
        finish();
    }
}