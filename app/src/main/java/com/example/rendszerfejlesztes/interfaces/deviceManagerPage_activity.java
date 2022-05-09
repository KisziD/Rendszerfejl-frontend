package com.example.rendszerfejlesztes.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.device.categoryCreator_activity;
import com.example.rendszerfejlesztes.device.deviceManager_activity;

public class deviceManagerPage_activity extends AppCompatActivity {

    Button dev_man, cat_adder, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_manager_page);

        dev_man = findViewById(R.id.dev_man_btn_dmp);
        cat_adder = findViewById(R.id.add_cat_btn_dmp);
        back = findViewById(R.id.back_bt6);

        getSupportActionBar().hide();

        dev_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dev = new Intent(getApplicationContext(), deviceManager_activity.class);
                startActivity(dev);
            }
        });

        cat_adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cat = new Intent(getApplicationContext(), categoryCreator_activity.class);
                startActivity(cat);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent back = new Intent(getApplicationContext(), login_activity.class);
                    startActivity(back);
            }
        });
    }
}