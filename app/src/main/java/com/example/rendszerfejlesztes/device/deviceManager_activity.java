package com.example.rendszerfejlesztes.device;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.interfaces.adminPage_activity;
import com.example.rendszerfejlesztes.interfaces.deviceManagerPage_activity;
import com.example.rendszerfejlesztes.models.DeviceModel;
import com.example.rendszerfejlesztes.services.deviceServices;
import com.example.rendszerfejlesztes.services.loginServices;

import java.util.ArrayList;

public class deviceManager_activity extends AppCompatActivity {

    Button dev_creator, back;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_manager);

        dev_creator = findViewById(R.id.new_dev_bt);
        list = findViewById(R.id.list);
        back = findViewById(R.id.back_bt3);

        getSupportActionBar().hide();

        deviceServices.getDevices(new deviceServices.VolleyResponseGETDEVICESListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(deviceManager_activity.this, message , Toast.LENGTH_LONG).show();
            }
            @Override
            public void onResponse(ArrayList<DeviceModel> deviceModels) {
                    ArrayAdapter arrayAdapter = new ArrayAdapter(deviceManager_activity.this, android.R.layout.simple_list_item_1, deviceModels);
                    list.setAdapter(arrayAdapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent ii = new Intent(deviceManager_activity.this, deviceInformation_activity.class);
                        ii.putExtra("SELECTED_ID",deviceModels.get(i).id);
                        startActivity(ii);
                    }
                });
            }
        });

        dev_creator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dev = new Intent(getApplicationContext(), deviceCreator_activity.class);
                startActivity(dev);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginServices.getSpeciality().equals("Admin"))
                {
                    Intent back = new Intent(getApplicationContext(), adminPage_activity.class);
                    startActivity(back);
                }else if (loginServices.getSpeciality().equals("Device manager"))
                {
                    Intent back = new Intent(getApplicationContext(), deviceManagerPage_activity.class);
                    startActivity(back);
                }
            }
        });
    }
}