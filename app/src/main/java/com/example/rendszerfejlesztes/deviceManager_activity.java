package com.example.rendszerfejlesztes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class deviceManager_activity extends AppCompatActivity {

    Button devicecreator;
    private Spinner spinner;
    RecyclerView list;

    //ArrayList<String> categoriaList = new ArrayList<>();
    String[] categories = new String[]{};
    ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_manager);

        devicecreator = findViewById(R.id.eszkoz_fel);

        Spinner dropdown = findViewById(R.id.legordulo);
        dropdown.setAdapter(categoriaAdapter);

        list = findViewById(R.id.lista);

        devicecreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uj = new Intent(getApplicationContext(), deviceCreator_activty.class);
                startActivity(uj);
            }

            String category = spinner.getSelectedItem().toString();         //kategória lekérés legördülő menüből




        });




    }
}