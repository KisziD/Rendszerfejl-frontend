package com.example.rendszerfejlesztes.device;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.models.DeviceModel;
import com.example.rendszerfejlesztes.services.deviceServices;

import java.util.ArrayList;

public class deviceCreator_activity extends AppCompatActivity {

    EditText name, place, desc, cateID;
    Button add, back;
    Spinner categories;
    deviceServices dService = new deviceServices(deviceCreator_activity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_creator);

        categories = findViewById(R.id.spinner_cate);
        name = findViewById(R.id.name_et);
        place = findViewById(R.id.place_et);
        desc = findViewById(R.id.desc_et);
        add = findViewById(R.id.add_bt);
        back = findViewById(R.id.back_bt);
        cateID = findViewById(R.id.cateID);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dService.addDevice(Integer.parseInt(cateID.getText().toString()),name.getText().toString(),place.getText().toString(),desc.getText().toString(), new deviceServices.VolleyResponsePOSTListener() {
                    @Override
                    public void onError(String message) {
                        Log.d("Error", message);
                    }

                    @Override
                    public void onResponse(String message) {
                        Toast.makeText(deviceCreator_activity.this, "OK", Toast.LENGTH_LONG).show();
                    }
                });

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