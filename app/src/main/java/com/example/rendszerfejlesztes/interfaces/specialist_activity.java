package com.example.rendszerfejlesztes.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.maintanance.taskManager_activity;

public class specialist_activity extends AppCompatActivity {

    Button task_man;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist);

        task_man = findViewById(R.id.task_man_sp);

        getSupportActionBar().hide();

        task_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent task = new Intent(getApplicationContext(), taskManager_activity.class);
                startActivity(task);
            }
        });
    }
}