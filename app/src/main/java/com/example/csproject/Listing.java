package com.example.csproject;

import android.media.Image;

public class Listing {
    private String condition, description, location, listId;
    private int rent;
    private Image image1,image2,image3,image4;

    public Listing() {
    }

    public Listing(String condition, String description, String location, String listId, int rent, Image image1, Image image2, Image image3, Image image4) {
        this.condition = condition;
        this.description = description;
        this.location = location;
        this.listId = listId;
        this.rent = rent;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
    }

    public Listing(String condition, String description, String location, String listId, int rent) {
        this.condition = condition;
        this.description = description;
        this.location = location;
        this.listId = listId;
        this.rent = rent;
    }
}
