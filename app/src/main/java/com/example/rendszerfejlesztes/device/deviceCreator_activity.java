package com.example.rendszerfejlesztes.device;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.services.deviceServices;

public class deviceCreator_activity extends AppCompatActivity {

    EditText name, place, desc;
    Button add, back;
    deviceServices dService = new deviceServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_creator);

        name = findViewById(R.id.name_et);
        place = findViewById(R.id.place_et);
        desc = findViewById(R.id.desc_et);
        add = findViewById(R.id.add_bt);
        back = findViewById(R.id.back_bt);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dService.addDevice(name.getText().toString(),place.getText().toString(),desc.getText().toString());
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getApplicationContext(),deviceManager_activity.class);
                startActivity(b);
            }
        });
    }
}