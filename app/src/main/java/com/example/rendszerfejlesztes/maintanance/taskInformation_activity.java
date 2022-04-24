package com.example.rendszerfejlesztes.maintanance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.models.TaskModel;
import com.example.rendszerfejlesztes.services.maintenanceServices;

public class taskInformation_activity extends AppCompatActivity {

    EditText name, date, state;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_information);

        name = findViewById(R.id.dname_t);
        date = findViewById(R.id.date_t);
        state = findViewById(R.id.state_t);
        back = findViewById(R.id.back_bt5);

        getSupportActionBar().hide();

        Integer devID = 0;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                devID = null;
            } else {
                devID = extras.getInt("SELECTED_ID");
            }
        }else{
            devID = (Integer) savedInstanceState.getSerializable("SELECTED_ID");
        }

        maintenanceServices.getTask(devID, new maintenanceServices.VolleyResponseGETTASKListener() {
            @Override
            public void onResponse(TaskModel tm) {
                name.setText(tm.getDev_name());
                date.setText(tm.getDate());
                state.setText(tm.getState());
            }
            @Override
            public void onError(String message) {
                Toast.makeText(taskInformation_activity.this, message, Toast.LENGTH_LONG).show();
            }
    });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), taskManager_activity.class);
                startActivity(back);
            }
        });
    }
}