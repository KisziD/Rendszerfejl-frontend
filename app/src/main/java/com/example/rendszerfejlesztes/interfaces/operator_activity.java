package com.example.rendszerfejlesztes.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.maintanance.maintenanceCreator_activity;
import com.example.rendszerfejlesztes.maintanance.taskManager_activity;

public class operator_activity extends AppCompatActivity {

    Button main_cre, task_man, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);

        main_cre = findViewById(R.id.main_cre_op);
        back = findViewById(R.id.back_bt7);
        task_man = findViewById(R.id.task_man_op);


        getSupportActionBar().hide();

        main_cre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getApplicationContext(), maintenanceCreator_activity.class);
                startActivity(main);
            }
        });

        task_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent task = new Intent(getApplicationContext(), taskManager_activity.class);
                startActivity(task);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), login_activity.class);
                startActivity(back);
            }
        });
    }
}