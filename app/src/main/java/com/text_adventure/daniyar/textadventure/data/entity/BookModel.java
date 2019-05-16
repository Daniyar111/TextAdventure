package com.text_adventure.daniyar.textadventure.data.entity;

import java.io.Serializable;

public class BookModel implements Serializable {

    private String name;
    private int imageId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
