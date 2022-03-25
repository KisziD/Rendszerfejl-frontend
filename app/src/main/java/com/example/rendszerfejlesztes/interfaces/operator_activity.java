package com.example.rendszerfejlesztes.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.services.loginServices;

public class operator_activity extends AppCompatActivity {

    TextView userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);

        userData = findViewById(R.id.datas);

        loginServices.getData(userData,getIntent());
    }
}