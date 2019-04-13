package com.example.madmovegame.home.model;

import android.media.Image;

public class Sport {
    private Image image;
    private String name;

    public Sport(String name) {
        this.name = name;
    }

    public Sport(Image image) {
        this.image = image;
    }

    public Sport(Image image, String name) {
        this.image = image;
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
