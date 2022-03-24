package com.example.rendszerfejlesztes.device;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.services.specialityServices;

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