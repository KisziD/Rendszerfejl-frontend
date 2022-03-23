package com.example.rendszerfejlesztes.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.interfaces.adminPage_activity;
import com.example.rendszerfejlesztes.interfaces.devicePerson_activity;
import com.example.rendszerfejlesztes.interfaces.operator_activity;
import com.example.rendszerfejlesztes.interfaces.repairer_activity;

public class login_activity extends AppCompatActivity {

    EditText username, pwd;
    String speciality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usern_et);
        pwd = findViewById(R.id.pass_et);
    }

    public void login (View v) {
        //az adatbázisból leellenőrözni, hogy helyesek-e a belé pési adatok
        //majd lekérni milyen beosztású a dolgozó: admin/eszkozfelelos/operator/karbantarto
        Intent i = new Intent();
        switch(speciality) {
            case "admin":
                i.setClass(this, adminPage_activity.class);
                sendData(i);
                break;
            case "devicePerson":
                i.setClass(this, devicePerson_activity.class);
                sendData(i);
                break;
            case "operator":
                i.setClass(this, operator_activity.class);
                sendData(i);
                break;
            case "repairman":
                i.setClass(this, repairer_activity.class);
                sendData(i);
                break;
            default:
                Toast.makeText(this,"No such user is in the system",Toast.LENGTH_LONG).show();
        }
    }

    private void sendData(Intent i) {
        i.putExtra("user", username.getText().toString());
        i.putExtra("job", speciality);
        setResult(RESULT_OK,i);
        startActivity(i);
        finish();
    }
}