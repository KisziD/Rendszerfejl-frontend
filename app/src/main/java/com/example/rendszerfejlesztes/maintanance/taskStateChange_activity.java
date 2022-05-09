package com.example.rendszerfejlesztes.maintanance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.models.TaskModel;
import com.example.rendszerfejlesztes.services.maintenanceServices;

public class taskStateChange_activity extends AppCompatActivity {

    TextView name, date, state, inst;
    EditText reason;
    Button save, back;
    Spinner spinner;
    Integer maintID, UserID;
    Integer s = 0;
    String[] states = {"Accepted", "Denied", "Started", "Done"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_state_change);

        name = findViewById(R.id.name_tsc);
        date = findViewById(R.id.date_tsc);
        state = findViewById(R.id.state_tsc);
        inst = findViewById(R.id.inst_tsc);
        reason = findViewById(R.id.reason_tsc);
        spinner = findViewById(R.id.spin_tsc);
        save = findViewById(R.id.save_tsc);
        back = findViewById(R.id.back_bt_tsc);

        SharedPreferences sharedPreferences = this.getSharedPreferences("Rendszerfejlesztes", Context.MODE_PRIVATE);
        UserID = sharedPreferences.getInt("id", 0);

        ArrayAdapter<String> spinArrayAdapter = new ArrayAdapter<>(taskStateChange_activity.this, android.R.layout.simple_spinner_item, states);
        spinArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinArrayAdapter);

        getSupportActionBar().hide();



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                maintID = null;
            } else {
                maintID = extras.getInt("MAINTENANCE_ID");
            }
        }else {
            maintID = (Integer) savedInstanceState.getSerializable("MAINTENANCE_ID");
        }

            maintenanceServices.getTask(maintID, new maintenanceServices.VolleyResponseGETTASKListener() {
                @Override
                public void onResponse(TaskModel tm) {
                    name.setText(tm.getDev_name());
                    date.setText(tm.getDate());
                    state.setText(tm.getState());
                    if (state.getText().toString().equals("Started"))
                    {
                        inst.setText(tm.getInstructions());
                    }

                }

                @Override
                public void onError(String message) {
                    Toast.makeText(taskStateChange_activity.this, message, Toast.LENGTH_LONG).show();
                }
            });


            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    s = (int)spinner.getSelectedItemId()+1;
                    maintenanceServices.changeState(maintID, s , UserID , reason.getText().toString(), new maintenanceServices.VolleyResponseSTATEPOSTListener() {
                        @Override
                        public void onResponse(String message) {
                            Toast.makeText(taskStateChange_activity.this, message, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onError(String message) {
                            Toast.makeText(taskStateChange_activity.this, message, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent back = new Intent(getApplicationContext(), taskList_activity.class);
                    startActivity(back);
                }
            });

        }
}