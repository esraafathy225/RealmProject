package com.esraa.hp.realmproject;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Contact extends RealmObject{
String comments="عشان اعرف ال table اللى هحطه في الداتابيز هخليه بي extend RealmObject ";

    @PrimaryKey
    private int id;

    private String name;

    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
