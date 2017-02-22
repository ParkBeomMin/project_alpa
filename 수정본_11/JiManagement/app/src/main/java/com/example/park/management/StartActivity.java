package com.example.park.management;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Intent intent = new Intent(StartActivity.this,MyService.class);
        startService(intent);

        //startService(new Intent("com.example.park.management"));
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Intent registerIntent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(registerIntent);
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0, 2000);

    }
}
