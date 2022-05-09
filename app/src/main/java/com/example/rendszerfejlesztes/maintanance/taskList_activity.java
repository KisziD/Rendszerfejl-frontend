package com.example.rendszerfejlesztes.maintanance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.interfaces.login_activity;
import com.example.rendszerfejlesztes.models.TaskModel;
import com.example.rendszerfejlesztes.services.maintenanceServices;

import java.util.ArrayList;

public class taskList_activity extends AppCompatActivity {

    Integer UserID;
    ListView lista;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        lista = findViewById(R.id.Listview_tasks);
        back = findViewById(R.id.back_bt8);

        getSupportActionBar().hide();

        SharedPreferences sharedPreferences = this.getSharedPreferences("Rendszerfejlesztes", Context.MODE_PRIVATE);
        UserID = sharedPreferences.getInt("id", 0);

        maintenanceServices.getTasks2(UserID, new maintenanceServices.VolleyResponseGETTASKSListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(ArrayList<TaskModel> taskModels) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(taskList_activity.this, android.R.layout.simple_list_item_1, taskModels);
                lista.setAdapter(arrayAdapter);
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent n = new Intent(taskList_activity.this, taskStateChange_activity.class);
                        n.putExtra("MAINTENANCE_ID", taskModels.get(i).id);
                        startActivity(n);
                    }
                });
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