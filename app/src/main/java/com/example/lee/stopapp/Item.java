package com.example.lee.stopapp;


import android.graphics.drawable.Drawable;

public class Item {
    public Item(Drawable image, String name, String root) {
        this.image = image;
        this.name = name;
        this.root = root;
    }

    private Drawable image;
    private String name;
    private String root;

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
}
