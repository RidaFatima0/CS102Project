package com.example.csproject;

import android.media.Image;
import android.widget.ImageView;

public class UserData {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String profilePic;

    public UserData(String firstName, String lastName, String email, String phoneNumber, String password, String profilePic){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.profilePic = profilePic;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getPassword(){
        return password;
    }

    public String getProfilePic(){
        return profilePic;
    }
}

