package com.example.rendszerfejlesztes.device;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.services.categoryServices;
import com.example.rendszerfejlesztes.services.specialityServices;

import java.util.List;

public class specialistCreator_activity extends AppCompatActivity {

    EditText spec;
    Spinner spinner;
    Button add, back;
    specialityServices dServices = new specialityServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_creator);

        spec = findViewById(R.id.spec_et);
        spinner = findViewById(R.id.spinner_categ);
        add = findViewById(R.id.add_btn);
        back = findViewById(R.id.back_btn);


        categoryServices.getCategoryNames(new categoryServices.VolleyResponseCATListener() {
            @Override
            public void onResponse(List<String> ls) {
                String[] names = ls.toArray(new String[ls.size()]);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                        specialistCreator_activity.this, android.R.layout.simple_spinner_item, names);
                spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
                spinner.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onError(String message) {
                Log.d("ERROR", message);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dServices.addSpeciality(spec.getText().toString(), spinner.getSelectedItem().toString());
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}