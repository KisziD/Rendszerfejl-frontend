package com.example.rendszerfejlesztes.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.rendszerfejlesztes.R;

public class repairer_activity extends AppCompatActivity {

    TextView userDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repairer);

        userDatas = findViewById(R.id.datas);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String u = getIntent().getStringExtra("user");
            String j = getIntent().getStringExtra("job");
            userDatas.setText(u + " (" + j + ")");
        }
    }
}