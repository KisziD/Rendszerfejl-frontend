package com.example.rendszerfejlesztes.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.rendszerfejlesztes.R;

public class adminPage_activity extends AppCompatActivity {
    TextView userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        userData = findViewById(R.id.datas);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String u = getIntent().getStringExtra("user");
            String j = getIntent().getStringExtra("job");
            userData.setText(u + " (" + j + ")");
        }
    }

}