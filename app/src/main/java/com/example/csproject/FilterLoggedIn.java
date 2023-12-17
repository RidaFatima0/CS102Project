package com.example.csproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;

public class FilterLoggedIn extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ImageView closebutton, profile;
    Button clear, applyFilter;
    RadioButton agriculture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_logged_in);

        closebutton = findViewById(R.id.closebutton);
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
                startActivity(intent);
                finish();
            }
        });

        clear = findViewById(R.id.clearbutton);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FilterLoggedIn.class);
                startActivity(intent);
                finish();
            }
        });

        agriculture = findViewById(R.id.radioButtonagriculture);
        applyFilter = findViewById(R.id.applyfilterbutton);
        applyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (agriculture.isChecked()){
                    Intent intent = new Intent(getApplicationContext(), Agriculturemainmenu.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(FilterLoggedIn.this, profile);
                popup.setOnMenuItemClickListener(FilterLoggedIn.this);
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
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), mainMenuLoggedIn.class);
        startActivity(intent);
        finish();
    }
}