package com.example.rendszerfejlesztes.device;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.services.categoryServices;
import com.example.rendszerfejlesztes.services.maintenanceServices;

public class maintenanceCreator_activity extends AppCompatActivity {

    EditText deviceID, date, instructions;
    Button add, back;
    maintenanceServices mServices = new maintenanceServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_creator);

        deviceID = findViewById(R.id.deviceID_et);
        date = findViewById(R.id.date_et);
        instructions = findViewById(R.id.instructions_et);
        add = findViewById(R.id.add_b);
        back = findViewById(R.id.back_b);

        getSupportActionBar().hide();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mServices.addCategory(deviceID.getText().toString(), date.getText().toString(), instructions.getText().toString(), new categoryServices.VolleyResponsePOSTListener() {
                    @Override
                    public void onResponse(String message) {
                        Toast.makeText(maintenanceCreator_activity.this, message, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(String message) {
                        Toast.makeText(maintenanceCreator_activity.this, message, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), deviceManager_activity.class);
                startActivity(back);
            }
        });
    }
}