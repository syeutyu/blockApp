package com.example.lee.stopapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.example.lee.stopapp.Model.User;

import java.util.Date;
import java.util.UUID;

import io.realm.Realm;

public class Splash extends Activity {
    public static Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Realm.init(getApplicationContext());
                    realm = Realm.getDefaultInstance();
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent;
                User user = realm.where(User.class).findFirst();
                if (user == null) {
                    intent = new Intent(getApplicationContext(), InfoActivity.class);
                } else {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    Snackbar.make(getCurrentFocus(),user.getName()+"님 어서오세요",Snackbar.LENGTH_SHORT).show();
                }

                startActivity(intent);
                finish();
            }
        }).start();

    }
}
