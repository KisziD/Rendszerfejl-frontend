package com.example.rendszerfejlesztes.speciality;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.services.specialistServices;
import com.example.rendszerfejlesztes.services.specialityServices;

public class specialistAdder_activity extends AppCompatActivity {

    EditText name;
    Button add;
    specialistServices sServices = new specialistServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_adder);

        getSupportActionBar().hide();

        add = findViewById(R.id.add_btn_sad);
        name = findViewById(R.id.name_et_sad);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sServices.addSpecialist(name.getText().toString(), new specialistServices.VolleyResponsePOSTListener() {
                    @Override
                    public void onResponse(String message) {
                        Toast.makeText(specialistAdder_activity.this, message, Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onError(String message) {
                        Toast.makeText(specialistAdder_activity.this, message, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}