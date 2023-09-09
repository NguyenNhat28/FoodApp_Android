package com.example.foodapp.AppFood;

import android.net.Uri;

public class AppFood {
    int Id;
    Uri img;
    String name;

    public AppFood(int id, Uri img, String name) {
        Id = id;
        this.img = img;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "" + Id + "";
    }
}