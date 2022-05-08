package com.example.rendszerfejlesztes.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.device.deviceManager_activity;
import com.example.rendszerfejlesztes.speciality.specialityCreator_activity;

public class deviceManagerPage_activity extends AppCompatActivity {

    Button dev_man, spec_adder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_manager_page);

        dev_man = findViewById(R.id.dev_man_btn_dmp);
        spec_adder = findViewById(R.id.new_spec_btn_dmp);


        getSupportActionBar().hide();

        dev_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dev = new Intent(getApplicationContext(), deviceManager_activity.class);
                startActivity(dev);
            }
        });

        spec_adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spec = new Intent(getApplicationContext(), specialityCreator_activity.class);
                startActivity(spec);
            }
        });
    }
}