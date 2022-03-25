package com.example.rendszerfejlesztes.device;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.services.deviceServices;

public class deviceInformation_activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_information);

                deviceServices dS = new deviceServices(deviceInformation_activity.this);
                String deviceID = deviceServices.getDevice(/* ID */);



    }

}