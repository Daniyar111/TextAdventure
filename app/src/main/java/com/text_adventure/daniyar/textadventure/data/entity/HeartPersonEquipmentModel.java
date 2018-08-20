package com.text_adventure.daniyar.textadventure.data.entity;

import io.realm.RealmObject;

public class HeartPersonEquipmentModel extends RealmObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeartPersonEquipmentModel{" +
                "name='" + name + '\'' +
                '}';
    }
}