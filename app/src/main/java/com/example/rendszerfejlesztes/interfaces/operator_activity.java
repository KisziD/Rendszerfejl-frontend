package com.example.rendszerfejlesztes.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.maintanance.maintenanceCreator_activity;

public class operator_activity extends AppCompatActivity {

    Button main_cre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);

        main_cre = findViewById(R.id.main_cre_op);


        getSupportActionBar().hide();

        main_cre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getApplicationContext(), maintenanceCreator_activity.class);
                startActivity(main);
            }
        });

        


    }
}