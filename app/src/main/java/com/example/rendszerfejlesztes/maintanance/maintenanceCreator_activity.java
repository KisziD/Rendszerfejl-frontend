package com.example.rendszerfejlesztes.maintanance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.interfaces.adminPage_activity;
import com.example.rendszerfejlesztes.models.DeviceModel;
import com.example.rendszerfejlesztes.services.categoryServices;
import com.example.rendszerfejlesztes.services.deviceServices;
import com.example.rendszerfejlesztes.services.maintenanceServices;

import java.util.ArrayList;
import java.util.List;

public class maintenanceCreator_activity extends AppCompatActivity {

    EditText instructions;
    Button add, back;
    Spinner spin;
    maintenanceServices mServices = new maintenanceServices();
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_creator);

        instructions = findViewById(R.id.instructions_et);
        add = findViewById(R.id.add_b);
        back = findViewById(R.id.back_b);
        spin = findViewById(R.id.spin_devID);

        list = new ArrayList<String>();

        getSupportActionBar().hide();

        deviceServices.getDevices(new deviceServices.VolleyResponseGETDEVICESListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(maintenanceCreator_activity.this, message , Toast.LENGTH_LONG).show();
            }
            @Override
            public void onResponse(ArrayList<DeviceModel> deviceModels) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(maintenanceCreator_activity.this, android.R.layout.simple_list_item_1, deviceModels);
                for(int i=0;i<deviceModels.size();i++) {
                    list.add(deviceModels.get(i).name + ": " + deviceModels.get(i).location);
                }
                String[] deviceIDs = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    deviceIDs[i] = list.get(i);
                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                        maintenanceCreator_activity.this, android.R.layout.simple_spinner_item, deviceIDs);
                spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
                spin.setAdapter(spinnerArrayAdapter);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mServices.addCategory(spin.getSelectedItem().toString(), instructions.getText().toString(), new categoryServices.VolleyResponsePOSTListener() {
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
                Intent back = new Intent(getApplicationContext(), adminPage_activity.class);
                startActivity(back);
            }
        });
    }
}