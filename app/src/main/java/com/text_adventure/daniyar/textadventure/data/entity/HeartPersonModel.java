package com.text_adventure.daniyar.textadventure.data.entity;

import io.realm.RealmList;
import io.realm.RealmObject;

public class HeartPersonModel extends RealmObject{

    private String name;
    private String description;
    private RealmList<HeartPersonSkillModel> skills;
    private int money;
    private int health;
    private int imageId;
    private int status;

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

    public RealmList<HeartPersonSkillModel> getSkills() {
        return skills;
    }

    public void setSkills(RealmList<HeartPersonSkillModel> skills) {
        this.skills = skills;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HeartPersonModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", skills=" + skills +
                ", money=" + money +
                ", health=" + health +
                ", imageId=" + imageId +
                ", status=" + status +
                '}';
    }
}