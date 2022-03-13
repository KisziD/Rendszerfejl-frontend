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

import java.util.ArrayList;

public class deviceManager_activity extends AppCompatActivity {

    Button devicecreator;
    Spinner spinner;
    RecyclerView list;

    ArrayList<String> categoryList = new ArrayList<>();  //need to be filled with categories
    String[] cats = categoryList.toArray(new String[categoryList.size()]);
    ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoryList);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_manager);
        devicecreator = findViewById(R.id.button_newdev);
        list = findViewById(R.id.list);

        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner dropdown = findViewById(R.id.spinner_cat);
        dropdown.setAdapter(categoriaAdapter);

        devicecreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(getApplicationContext(), deviceCreator_activity.class);
                startActivity(n);
            }

            String category = spinner.getSelectedItem().toString();



        });




    }
}