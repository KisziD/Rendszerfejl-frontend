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
import com.example.rendszerfejlesztes.interfaces.adminPage_activity;
import com.example.rendszerfejlesztes.services.categoryServices;

import java.util.List;

public class categoryCreator_activity extends AppCompatActivity {

    EditText cat, norm_h, period, instructions;
    Button add,back;
    Spinner spin;
    categoryServices cServices = new categoryServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_creator);

        cat = findViewById(R.id.cat_et);
        norm_h = findViewById(R.id.norm_h_et);
        period = findViewById(R.id.period_et);
        instructions = findViewById(R.id.instructions_et);
        add = findViewById(R.id.add_b);
        back  = findViewById(R.id.back_b);
        spin = findViewById(R.id.spin_addcate);

        getSupportActionBar().hide();

        categoryServices.getCategoryNames(new categoryServices.VolleyResponseCATListener() {
            @Override
            public void onResponse(List<String> ls) {
                String[] names = new String[ls.size()+1];
                names[0] = "No parent";
                for (int i = 0; i < ls.size(); i++) {
                    names[i+1] = ls.get(i);
                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                        categoryCreator_activity.this, android.R.layout.simple_spinner_item, names);
                spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
                spin.setAdapter(spinnerArrayAdapter);
            }
            @Override
            public void onError(String message) {
                Toast.makeText(categoryCreator_activity.this, message, Toast.LENGTH_LONG).show();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            cServices.addCategory(cat.getText().toString(), norm_h.getText().toString(), period.getText().toString(), instructions.getText().toString(), spin.getSelectedItem().toString(), new categoryServices.VolleyResponsePOSTListener() {
                @Override
                public void onResponse(String message) {
                    Toast.makeText(categoryCreator_activity.this, message, Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onError(String message) {
                    Toast.makeText(categoryCreator_activity.this, message, Toast.LENGTH_LONG).show();
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