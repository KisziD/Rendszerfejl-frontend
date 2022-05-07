package com.example.rendszerfejlesztes.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.maintanance.taskInformation_activity;
import com.example.rendszerfejlesztes.maintanance.taskManager_activity;
import com.example.rendszerfejlesztes.models.TaskConModel;
import com.example.rendszerfejlesztes.models.TaskModel;
import com.example.rendszerfejlesztes.services.maintenanceServices;

import java.util.ArrayList;
import java.util.List;

public class listTasks_activity extends AppCompatActivity {

    ListView tasklist;
    Integer specialistID;
    List<Integer> mlist;
    List<TaskModel> tlist, list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);

        tasklist = findViewById(R.id.list);

        SharedPreferences sharedPref = this.getSharedPreferences("Rendszerfejlesztes", Context.MODE_PRIVATE);
        specialistID = sharedPref.getInt("id", 0);

        maintenanceServices.getTaskConnection(new maintenanceServices.VolleyResponseGETTASKCONNECTIONListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(listTasks_activity.this, message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(ArrayList<TaskConModel> taskConModels) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(listTasks_activity.this, android.R.layout.simple_list_item_1, taskConModels);
                for (int i = 0; i < taskConModels.size(); i++) {
                    if (taskConModels.get(i).SpecialistID == specialistID)
                        mlist.add(taskConModels.get(i).MaintenanceID);
                }
            }
        });

        maintenanceServices.getTasks(new maintenanceServices.VolleyResponseGETTASKSListener() {
            @Override
            public void onResponse(ArrayList<TaskModel> taskModels) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(listTasks_activity.this, android.R.layout.simple_list_item_1, taskModels);
                for (int i = 0; i < taskModels.size(); i++) {
                    tlist.add(taskModels.get(i));
                }
            }
            @Override
            public void onError(String message) {
                Toast.makeText(listTasks_activity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        for(int i = 0; i < mlist.size(); i++)
            for(int j = 0; j < tlist.size(); j++)
                if (mlist.get(i).equals(tlist.get(j).getId()))
                    list.add(tlist.get(j));
        ArrayAdapter results = new ArrayAdapter<TaskModel>(this, android.R.layout.simple_list_item_1, list);
        tasklist.setAdapter(results);
    }
}