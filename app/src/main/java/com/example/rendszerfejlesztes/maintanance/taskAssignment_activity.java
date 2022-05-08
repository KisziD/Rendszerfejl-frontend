package com.example.rendszerfejlesztes.maintanance;

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
import com.example.rendszerfejlesztes.models.SpecialistModel;
import com.example.rendszerfejlesztes.models.TaskModel;
import com.example.rendszerfejlesztes.services.maintenanceServices;
import com.example.rendszerfejlesztes.services.specialistServices;

import java.util.ArrayList;
import java.util.List;

public class taskAssignment_activity extends AppCompatActivity {

    EditText name, date, state;
    Spinner spec;
    Button back, add;
    Integer taskID;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_assignment);

        name = findViewById(R.id.dname_ta);
        date = findViewById(R.id.date_ta);
        state = findViewById(R.id.state_ta);
        back = findViewById(R.id.back_bt5_ta);
        add = findViewById(R.id.add_btn_ta);
        spec = findViewById(R.id.spec_spin);

        list = new ArrayList<String>();

        getSupportActionBar().hide();

        taskID = 0;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                taskID = null;
            } else {
                taskID = extras.getInt("SELECTED_ID");
            }
        }else{
            taskID = (Integer) savedInstanceState.getSerializable("SELECTED_ID");
        }

        maintenanceServices.getTask(taskID, new maintenanceServices.VolleyResponseGETTASKListener() {
            @Override
            public void onResponse(TaskModel tm) {
                name.setText(tm.getDev_name());
                date.setText(tm.getDate());
                state.setText(tm.getState());
            }
            @Override
            public void onError(String message) {
                Toast.makeText(taskAssignment_activity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        specialistServices.getSpecialist(new specialistServices.VolleyResponseGETSPECListener() {
            @Override
            public void onResponse(ArrayList<SpecialistModel> specialistModels) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(taskAssignment_activity.this, android.R.layout.simple_list_item_1, specialistModels);
                for(int i=0;i<specialistModels.size();i++) {
                    list.add(specialistModels.get(i).id + ": " + specialistModels.get(i).name);
                }
                String[] names = list.toArray(new String[list.size()]);

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                        taskAssignment_activity.this, android.R.layout.simple_spinner_item, names);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spec.setAdapter(spinnerArrayAdapter);
            }
            @Override
            public void onError(String message) {
                Toast.makeText(taskAssignment_activity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maintenanceServices.postTaskID(taskID, spec.getSelectedItem().toString(), new maintenanceServices.VolleyResponsePOSTListener() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponse(String message) {

                    }
                });
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