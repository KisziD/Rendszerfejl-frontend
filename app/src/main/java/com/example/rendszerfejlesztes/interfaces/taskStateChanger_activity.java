package com.example.rendszerfejlesztes.interfaces;

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
import com.example.rendszerfejlesztes.models.TaskModel;
import com.example.rendszerfejlesztes.services.maintenanceServices;

public class taskStateChanger_activity extends AppCompatActivity {

    EditText name, date, state;
    Button save, back;
    Spinner spin;
    String[] states = {"Accepted", "Declined", "Started", "Finished"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_state_changer);

        name = findViewById(R.id.dname_t);
        date = findViewById(R.id.date_t);
        state = findViewById(R.id.state_t);
        save = findViewById(R.id.save_b);
        back = findViewById(R.id.back_bt6);
        spin = findViewById(R.id.spin_addcate);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                taskStateChanger_activity.this, android.R.layout.simple_spinner_item, states);
        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spin.setAdapter(spinnerArrayAdapter);

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
                Toast.makeText(taskStateChanger_activity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maintenanceServices.changeState(name.getText().toString(), date.getText().toString(), spin.getSelectedItem().toString(), new maintenanceServices.VolleyResponseSTATEPOSTListener() {
                    @Override
                    public void onResponse(String message) {
                        Toast.makeText(taskStateChanger_activity.this, message, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(String message) {
                        Toast.makeText(taskStateChanger_activity.this, message, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), listTasks_activity.class);
                startActivity(back);
            }
        });
    }
}