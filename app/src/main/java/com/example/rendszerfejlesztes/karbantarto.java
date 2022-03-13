package com.example.rendszerfejlesztes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class karbantarto extends AppCompatActivity {
    TextView adatok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karbantarto);

        adatok = findViewById(R.id.adatok);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String u = getIntent().getStringExtra("user");
            String j = getIntent().getStringExtra("job");
            adatok.setText(u + " (" + j + ")");
        }
    }
}