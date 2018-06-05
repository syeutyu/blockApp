package com.example.lee.stopapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lee.stopapp.databinding.SmsSendBinding;

import java.util.UUID;

public class sms_send extends AppCompatActivity {

    private SmsSendBinding sendBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendBinding = DataBindingUtil.setContentView(this,R.layout.sms_send);
        sendBinding.checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(sendBinding.checkNumber.getText().toString());
                if(sendBinding.checkNumber.getText().toString().equals("1234")){
                    Intent intent = new Intent();
                    intent.putExtra("code", UUID.randomUUID().toString().replace("-",""));
                    setResult(201,intent);
                    finish();
                }
            }
        });
    }
}
