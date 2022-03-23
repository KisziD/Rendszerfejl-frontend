package com.example.rendszerfejlesztes.device;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.services.categoryServices;

public class categoryCreator_activity extends AppCompatActivity {

    EditText cat;
    Spinner spinner;
    Button add,back;
    categoryServices dServices = new categoryServices();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_creator);

        cat = findViewById(R.id.cat_et);
        spinner = findViewById(R.id.spinner_rank);
        add = findViewById(R.id.add_b);
        back  = findViewById(R.id.back_b);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dServices.addCategory(cat.getText().toString(), spinner.getSelectedItem().toString());
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