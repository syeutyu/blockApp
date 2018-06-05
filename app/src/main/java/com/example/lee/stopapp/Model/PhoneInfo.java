package com.example.lee.stopapp.Model;

import java.io.Serializable;

import io.realm.RealmObject;

public class PhoneInfo extends RealmObject {

    private String number;

    private String code;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
