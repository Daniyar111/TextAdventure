package com.text_adventure.daniyar.textadventure.data.entity;

import io.realm.RealmList;
import io.realm.RealmObject;

public class HeartPersonSkillModel extends RealmObject {

    private String name;
    private String description;
    private RealmList<HeartPersonEquipmentModel> equipments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RealmList<HeartPersonEquipmentModel> getEquipments() {
        return equipments;
    }

    public void setEquipments(RealmList<HeartPersonEquipmentModel> equipments) {
        this.equipments = equipments;
    }

    @Override
    public String toString() {
        return "HeartPersonSkillModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", equipments=" + equipments +
                '}';
    }
}