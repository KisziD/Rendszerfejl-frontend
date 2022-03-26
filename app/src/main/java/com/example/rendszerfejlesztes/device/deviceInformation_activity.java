package com.example.rendszerfejlesztes.device;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.models.DeviceModel;
import com.example.rendszerfejlesztes.services.deviceServices;

import java.util.ArrayList;

public class deviceInformation_activity extends AppCompatActivity {

    EditText cat, name, place, desc;
    DeviceModel deviceModel = new DeviceModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_information);

        cat = findViewById(R.id.cat_t);
        name = findViewById(R.id.name_t);
        place = findViewById(R.id.place_t);
        desc = findViewById(R.id.desc_t);

        Integer devID = 0;
        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                devID = null;
            }else{
                devID = extras.getInt("SELECTED_ID");
            }
        }else{
            devID = (Integer) savedInstanceState.getSerializable("SELECTED_ID");
        }
                deviceServices dS = new deviceServices(deviceInformation_activity.this);
                deviceServices.getDevice(devID, new deviceServices.VolleyResponseGETDEVICEListener() {
                    @Override
                    public void onError(String message) {
                        Log.d("Error", message);
                      //  Toast.makeText(deviceInformation_activity.this, message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(DeviceModel deviceModel) {
                        cat.setText(deviceModel.getCatID().toString());
                        name.setText(deviceModel.getName());
                        place.setText(deviceModel.getLocation());
                        desc.setText(deviceModel.getDesc());
                    }
                });



    }

}