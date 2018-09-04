package com.text_adventure.daniyar.textadventure.data.entity;

import io.realm.RealmObject;

public class HeartPersonEquipmentModel extends RealmObject {

    private String name;
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "HeartPersonEquipmentModel{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}