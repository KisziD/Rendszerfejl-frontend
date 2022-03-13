package com.example.rendszerfejlesztes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, pwd;
    String beosztas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
    }

    public void belepes (View v) {
        //az adatbázisból leellenőrözni, hogy helyesek-e a belépési adatok
        //majd lekérni milyen beosztású a dolgozó: admin/eszkozfelelos/operator/karbantarto
        Intent i = new Intent();
        switch(beosztas) {
            case "admin":
                i.setClass(this, admin.class);
                sendData(i);
                break;
            case "eszkozfelelos":
                i.setClass(this, eszkozfelelos.class);
                sendData(i);
                break;
            case "operator":
                i.setClass(this, operator.class);
                sendData(i);
                break;
            case "karbantarto":
                i.setClass(this, karbantarto.class);
                sendData(i);
                break;
            default:
                Toast.makeText(this,"Nincs ilyen adatokkal rendelkező személy!",Toast.LENGTH_LONG).show();
        }
    }

    private void sendData(Intent i) {
        i.putExtra("user", username.getText().toString());
        i.putExtra("job", beosztas);
        setResult(RESULT_OK,i);
        startActivity(i);
        finish();
    }
}