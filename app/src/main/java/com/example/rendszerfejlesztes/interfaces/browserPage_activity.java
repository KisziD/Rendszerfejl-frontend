package com.example.rendszerfejlesztes.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.device.deviceManager_activity;
import com.example.rendszerfejlesztes.speciality.specialistAdder_activity;

public class browserPage_activity extends AppCompatActivity {

    Button dev_man, spec_adder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_page);

        dev_man = findViewById(R.id.dev_man_btn);
        spec_adder = findViewById(R.id.spec_adder_btn);


        dev_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(getApplicationContext(), deviceManager_activity.class);
                startActivity(n);
            }
        });

        spec_adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(getApplicationContext(), specialistAdder_activity.class);
                startActivity(n);
            }
        });

    }
}