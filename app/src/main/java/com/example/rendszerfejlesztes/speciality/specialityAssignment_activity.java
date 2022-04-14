package com.example.rendszerfejlesztes.speciality;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.interfaces.browserPage_activity;
import com.example.rendszerfejlesztes.services.specialityServices;
import com.example.rendszerfejlesztes.services.specialistServices;

import java.util.List;

public class specialityAssignment_activity extends AppCompatActivity {

    Spinner worker, spec;
    Button add, back;
    specialistServices specialistServices = new specialistServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciality_assignment);

        worker = findViewById(R.id.spin_worker);
        spec = findViewById(R.id.spin_spec);
        add = findViewById(R.id.add_btn_sa);
        back = findViewById(R.id.back_btn_sa);

        getSupportActionBar().hide();

        specialistServices.getSpecialist(new specialistServices.VolleyResponseGETSPECListener() {
            @Override
            public void onResponse(List<String> ls) {
                String[] names = ls.toArray(new String[ls.size()]);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                        specialityAssignment_activity.this, android.R.layout.simple_spinner_item, names);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                worker.setAdapter(spinnerArrayAdapter);
            }
            @Override
            public void onError(String message) {
                Toast.makeText(specialityAssignment_activity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        specialityServices.getSpeciality(new specialityServices.VolleyResponseGETSPECListener() {
            @Override
            public void onResponse(List<String> ls) {
                String[] names = ls.toArray(new String[ls.size()]);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                        specialityAssignment_activity.this, android.R.layout.simple_spinner_item, names);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spec.setAdapter(spinnerArrayAdapter);
            }
            @Override
            public void onError(String message) {
                Toast.makeText(specialityAssignment_activity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                specialistServices.addSpecialistplus(worker.getSelectedItem().toString(), spec.getSelectedItem().toString(), new specialistServices.VolleyResponsePOSTListener() {
                    @Override
                    public void onResponse(String message) {
                        Toast.makeText(specialityAssignment_activity.this, message, Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onError(String message) {
                        Toast.makeText(specialityAssignment_activity.this, message, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

       back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), browserPage_activity.class);
                startActivity(back);
            }
        });
    }
}