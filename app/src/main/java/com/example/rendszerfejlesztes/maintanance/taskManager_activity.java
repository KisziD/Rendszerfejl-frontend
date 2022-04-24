package com.example.rendszerfejlesztes.maintanance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.interfaces.browserPage_activity;
import com.example.rendszerfejlesztes.models.TaskModel;
import com.example.rendszerfejlesztes.services.maintenanceServices;

import java.util.ArrayList;

public class taskManager_activity extends AppCompatActivity {

    Button back;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);

        list = findViewById(R.id.list2);
        back = findViewById(R.id.back_bt4);

        getSupportActionBar().hide();

       maintenanceServices.getTasks(new maintenanceServices.VolleyResponseGETTASKSListener() {
           @Override
           public void onResponse(ArrayList<TaskModel> taskModels) {
               ArrayAdapter arrayAdapter = new ArrayAdapter(taskManager_activity.this, android.R.layout.simple_list_item_1, taskModels);
               list.setAdapter(arrayAdapter);
               list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                       Intent n = new Intent(taskManager_activity.this, taskInformation_activity.class);
                       n.putExtra("SELECTED_ID", taskModels.get(i).id);
                       startActivity(n);
                   }
               });
           }
           @Override
           public void onError(String message) {
               Toast.makeText(taskManager_activity.this, message, Toast.LENGTH_LONG).show();
           }
       });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), browserPage_activity.class);
                startActivity(back);
            }
        });
    }
}