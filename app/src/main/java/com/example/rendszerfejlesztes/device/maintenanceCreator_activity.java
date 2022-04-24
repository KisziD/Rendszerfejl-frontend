package com.example.rendszerfejlesztes.device;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.models.DeviceModel;
import com.example.rendszerfejlesztes.services.categoryServices;
import com.example.rendszerfejlesztes.services.deviceServices;
import com.example.rendszerfejlesztes.services.maintenanceServices;

import java.util.ArrayList;
import java.util.List;

public class maintenanceCreator_activity extends AppCompatActivity {

    EditText date, instructions;
    Button add, back;
    maintenanceServices mServices = new maintenanceServices();
    Spinner spin;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_creator);

        date = findViewById(R.id.date_et);
        instructions = findViewById(R.id.instructions_et);
        add = findViewById(R.id.add_b);
        back = findViewById(R.id.back_b);
        spin = findViewById(R.id.spin_devID);

        getSupportActionBar().hide();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mServices.addCategory(spin.getSelectedItem().toString(), date.getText().toString(), instructions.getText().toString(), new categoryServices.VolleyResponsePOSTListener() {
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

        deviceServices.getDevices(new deviceServices.VolleyResponseGETDEVICESListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(maintenanceCreator_activity.this, message , Toast.LENGTH_LONG).show();
            }
            @Override
            public void onResponse(ArrayList<DeviceModel> deviceModels) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(maintenanceCreator_activity.this, android.R.layout.simple_list_item_1, deviceModels);
                for(int i=0;i<deviceModels.size();i++) {
                    list.add(Integer.toString(deviceModels.get(i).id));
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
    }
}