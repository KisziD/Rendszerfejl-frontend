package com.example.rendszerfejlesztes.device;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.rendszerfejlesztes.R;

public class deviceManager_activity extends AppCompatActivity {

    Button devicecreator, categoryadder;
    Spinner spinner;
    RecyclerView list;

    //ArrayList<String> categoriaList = new ArrayList<>();
    String[] categories = new String[]{};
    ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_manager);

        categoryadder = findViewById(R.id.button_newcat);
        devicecreator = findViewById(R.id.button_newdev);
        list = findViewById(R.id.list);

        Spinner dropdown = findViewById(R.id.spinner_cat);
        dropdown.setAdapter(categoriaAdapter);

        String category = spinner.getSelectedItem().toString();         //kategória lekérés legördülő menüből

        devicecreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uj = new Intent(getApplicationContext(), deviceManager_activity.class);
                startActivity(uj);
            }
        });

        categoryadder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uj = new Intent(getApplicationContext(), categoryCreator_activity.class);
                startActivity(uj);
            }
        });




    }
}