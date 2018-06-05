package com.example.lee.stopapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lee.stopapp.databinding.ActivitySettingBinding;

public class Setting extends AppCompatActivity {

    private ActivitySettingBinding settingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting);

    }
}
