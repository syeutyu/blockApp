package com.example.lee.stopapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import com.example.lee.stopapp.Model.PhoneInfo;
import com.example.lee.stopapp.Model.User;
import com.example.lee.stopapp.databinding.InfoBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.RealmList;


public class InfoActivity extends AppCompatActivity {

    private InfoBinding infoBinding;
    private Realm realms;
    RealmList<PhoneInfo> list = new RealmList<>();
    private final Pattern regPhone = Pattern.compile("^\\d{3}-\\d{3,4}-\\d{4}$");

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 201){
            realms.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    PhoneInfo phoneInfo = new PhoneInfo();
                    phoneInfo.setNumber(infoBinding.number.getText().toString());
                    phoneInfo.setCode(data.getStringExtra("code"));
                    list.add(phoneInfo);
                    realm.insert(list);
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    Snackbar.make(getCurrentFocus(),"저장 완료",Snackbar.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realms = Realm.getDefaultInstance();
        try {
            infoBinding = DataBindingUtil.setContentView(this, R.layout.info);
            Intent intent = getIntent();
            String item = intent.getStringExtra("imagePath");
            infoBinding.appLogo.setImageDrawable(getPackageManager().getApplicationIcon(item));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        infoBinding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = infoBinding.number.getText().toString();
                Matcher m = regPhone.matcher(number);
                if (m.find()) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(infoBinding.number.getText().toString(), null, "테스트", null, null);
                    registerReceiver(new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            switch (getResultCode()) {
                                case Activity.RESULT_OK:
                                    Toast.makeText(getApplicationContext(), "전송 완료", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    }, new IntentFilter("SMS_SENT_ACTION"));
                    Intent intent = new Intent(getApplicationContext(), sms_send.class);
                    startActivityForResult(intent,201);
                } else {
                    Toast.makeText(getApplicationContext(), "올바른 전화번호 형식을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        infoBinding.saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realms.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        User user = new User();
                        user.setName(infoBinding.name.getText().toString());
                        user.setCreate_at(new Date());
                        user.setInfo(list);
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Snackbar.make(getCurrentFocus(),"개인 정보 저장 완료",Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}