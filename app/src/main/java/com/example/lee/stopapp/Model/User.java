package com.example.lee.stopapp.Model;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

public class User extends RealmObject {

    private String name;

    private Date create_at;

    private boolean sign_check;

    public boolean getSign_check() {
        return sign_check;
    }

    public void setSign_check(boolean sign_check) {
        this.sign_check = sign_check;
    }

    private RealmList<PhoneInfo> info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public RealmList<PhoneInfo> getInfo() {
        return info;
    }

    public void setInfo(RealmList<PhoneInfo> info) {
        this.info = info;
    }
}