package com.example.rendszerfejlesztes.speciality;

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
import com.example.rendszerfejlesztes.interfaces.adminPage_activity;
import com.example.rendszerfejlesztes.services.specialityServices;
import com.example.rendszerfejlesztes.services.specialistServices;

import java.util.List;

public class specialistAdder_activity extends AppCompatActivity {

    EditText name, username, password;
    Spinner  qualific;
    Button add, back;
    specialistServices specialistServices = new specialistServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_adder);

        name = findViewById(R.id.name_sa);
        username = findViewById(R.id.user_name_sa);
        password = findViewById(R.id.password_sa);
        qualific = findViewById(R.id.spin_qualific);
        add = findViewById(R.id.add_btn_sa);
        back = findViewById(R.id.back_btn_sa);

        getSupportActionBar().hide();

        specialityServices.getSpeciality(new specialityServices.VolleyResponseGETSPECListener() {
            @Override
            public void onResponse(List<String> ls) {
                String[] names = ls.toArray(new String[ls.size()]);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                        specialistAdder_activity.this, android.R.layout.simple_spinner_item, names);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                qualific.setAdapter(spinnerArrayAdapter);
            }
            @Override
            public void onError(String message) {
                Toast.makeText(specialistAdder_activity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                specialistServices.addSpecialist(name.getText().toString(), username.getText().toString(), password.getText().toString(), "Specialist", qualific.getSelectedItem().toString(), new specialistServices.VolleyResponsePOSTListener() {
                    @Override
                    public void onResponse(String message) {
                        Toast.makeText(specialistAdder_activity.this, message, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(String message) {
                        Toast.makeText(specialistAdder_activity.this, message, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

       back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), adminPage_activity.class);
                startActivity(back);
            }
        });
    }
}