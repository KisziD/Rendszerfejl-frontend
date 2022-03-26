package com.example.rendszerfejlesztes.device;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.models.DeviceModel;
import com.example.rendszerfejlesztes.services.deviceServices;

import java.util.ArrayList;

public class deviceManager_activity extends AppCompatActivity {

    Button devicecreator, categoryadder;
    Spinner spinner;
    ListView list;

    //String[] categories = new String[]{};
   // ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_manager);

        categoryadder = findViewById(R.id.button_newcat);
        devicecreator = findViewById(R.id.button_newdev);
        list = findViewById(R.id.list);

        //Spinner dropdown = findViewById(R.id.spinner_cat);
        //dropdown.setAdapter(categoriaAdapter);
        //String category = spinner.getSelectedItem().toString();         //kategória lekérés legördülő menüből



        deviceServices dS = new deviceServices(deviceManager_activity.this);
        deviceServices.getDevices(new deviceServices.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(deviceManager_activity.this, "Something wrong", Toast.LENGTH_LONG).show();
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





        devicecreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uj = new Intent(getApplicationContext(), deviceCreator_activity.class);
                startActivity(uj);
            }
        });

        categoryadder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uj = new Intent(getApplicationContext(), categoryCreator_activity.class);
                startActivity(uj);
            }
        });




    }
}