package com.example.rendszerfejlesztes.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.device.categoryCreator_activity;
import com.example.rendszerfejlesztes.device.deviceManager_activity;
import com.example.rendszerfejlesztes.maintanance.maintenanceCreator_activity;
import com.example.rendszerfejlesztes.maintanance.taskManager_activity;
import com.example.rendszerfejlesztes.speciality.specialityCreator_activity;
import com.example.rendszerfejlesztes.speciality.specialistAdder_activity;

public class adminPage_activity extends AppCompatActivity {

    Button dev_man, spec_adder, spect_adder, main_cre, cat_adder, task_man, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        dev_man = findViewById(R.id.dev_man_btn);
        task_man = findViewById(R.id.task_man_btn);
        cat_adder = findViewById(R.id.new_cat_bt);
        spec_adder = findViewById(R.id.new_spec_bt);
        spect_adder = findViewById(R.id.spect_adder_btn);
        main_cre = findViewById(R.id.main_cre);
        back = findViewById(R.id.back_bt10);

        getSupportActionBar().hide();

        dev_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dev = new Intent(getApplicationContext(), deviceManager_activity.class);
                startActivity(dev);
            }
        });

        task_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent task = new Intent(getApplicationContext(), taskManager_activity.class);
                startActivity(task);
            }
        });

        cat_adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cat = new Intent(getApplicationContext(), categoryCreator_activity.class);
                startActivity(cat);
            }
        });

        spec_adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spec = new Intent(getApplicationContext(), specialityCreator_activity.class);
                startActivity(spec);
            }
        });

        spect_adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spect = new Intent(getApplicationContext(), specialistAdder_activity.class);
                startActivity(spect);
            }
        });

        main_cre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getApplicationContext(), maintenanceCreator_activity.class);
                startActivity(main);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(getApplicationContext(), login_activity.class);
                startActivity(login);
            }
        });

    }
}