package com.text_adventure.daniyar.textadventure.data.entity;

import io.realm.RealmObject;

public class HeartStoryModel extends RealmObject {

    private int id;
    private int parentId;
    private String description;
    private int type;
    private int isOpened;
    private boolean isShowed;
    private boolean isChosen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsOpened() {
        return isOpened;
    }

    public void setIsOpened(int isOpened) {
        this.isOpened = isOpened;
    }

    public boolean getIsShowed() {
        return isShowed;
    }

    public void setIsShowed(boolean showed) {
        isShowed = showed;
    }

    public boolean getIsChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    @Override
    public String toString() {
        return "HeartStoryModel{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", isOpened=" + isOpened +
                ", isShowed=" + isShowed +
                ", isChosen=" + isChosen +
                '}';
    }
}
