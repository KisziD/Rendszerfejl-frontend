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
import com.example.rendszerfejlesztes.models.DeviceModel;
import com.example.rendszerfejlesztes.services.deviceServices;

import java.util.ArrayList;

public class deviceManager_activity extends AppCompatActivity {

    Button dev_creator, cat_adder, spec_adder;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_manager);

        cat_adder = findViewById(R.id.new_cat_bt);
        dev_creator = findViewById(R.id.new_dev_bt);
        spec_adder = findViewById(R.id.new_spec_bt);
        list = findViewById(R.id.list);

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
    }
}