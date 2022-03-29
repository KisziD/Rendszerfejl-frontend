package com.example.rendszerfejlesztes.device;

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
import com.example.rendszerfejlesztes.services.categoryServices;
import com.example.rendszerfejlesztes.services.specialtyServices;

import java.util.List;

public class specialityCreator_activity extends AppCompatActivity {

    EditText spec;
    Spinner spin;
    Button add, back;
    specialtyServices sServices = new specialtyServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_creator);

        spec = findViewById(R.id.spec_et);
        spin = findViewById(R.id.spin_categ);
        add = findViewById(R.id.add_btn);
        back = findViewById(R.id.back_btn);

        getSupportActionBar().hide();

        categoryServices.getCategoryNames(new categoryServices.VolleyResponseCATListener() {
            @Override
            public void onResponse(List<String> ls) {
                String[] names = ls.toArray(new String[ls.size()]);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                        specialityCreator_activity.this, android.R.layout.simple_spinner_item, names);
                spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
                spin.setAdapter(spinnerArrayAdapter);
            }
            @Override
            public void onError(String message) {
                Toast.makeText(specialityCreator_activity.this, message, Toast.LENGTH_LONG).show();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sServices.addSpeciality(spec.getText().toString(), spin.getSelectedItem().toString(), new specialtyServices.VolleyResponsePOSTListener() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("SUCCESS")) {
                            Toast.makeText(specialityCreator_activity.this, "Specialty added", Toast.LENGTH_SHORT ).show();
                        } else {
                            Toast.makeText(specialityCreator_activity.this, "Specialty already exists", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(String message) {
                        Toast.makeText(specialityCreator_activity.this, message, Toast.LENGTH_LONG).show();
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