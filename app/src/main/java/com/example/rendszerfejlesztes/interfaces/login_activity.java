package com.example.rendszerfejlesztes.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rendszerfejlesztes.R;
import com.example.rendszerfejlesztes.services.loginServices;

public class login_activity extends AppCompatActivity {

    EditText username, pwd;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usern_et);
        pwd = findViewById(R.id.pass_et);
        login = findViewById(R.id.login_bt);

        getSupportActionBar().hide();

        checkToken();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void checkToken () {
        loginServices ls = new loginServices(this);
        ls.checkToken(new loginServices.VolleyResponseTOKENListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(login_activity.this, message, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onResponse(String response) {
                    if(response.equals("SUCCESS")) {
                        switch(loginServices.getSpeciality()) {
                            case "Admin":
                                Intent a = new Intent(login_activity.this, adminPage_activity.class);
                                startActivity(a);
                                break;
                            case "Device manager":
                                Intent d = new Intent(login_activity.this, deviceManagerPage_activity.class);
                                startActivity(d);
                                break;
                            case "Operator":
                                Intent o = new Intent(login_activity.this, operator_activity.class);
                                startActivity(o);
                                break;
                            case "Specialist":
                                Intent s = new Intent(login_activity.this, specialist_activity.class);
                                startActivity(s);
                                break;
                           // default:
                              //  Toast.makeText(this,"No such user is in the system",Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(login_activity.this, "Login available", Toast.LENGTH_LONG).show();
                    }
            }
        });

    }

    public void login() {
        loginServices ls = new loginServices(this);
        ls.checkLogin(username.getText().toString(), pwd.getText().toString(), new loginServices.VolleyResponseLOGINListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(login_activity.this, message, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onResponse(String response) {
                if(response.equals("SUCCESS")) {
                    Intent i = new Intent(login_activity.this, adminPage_activity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(login_activity.this, "Login available", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

  /*  private void sendData(Intent i) {
        i.putExtra("user", username.getText().toString());
        i.putExtra("job", speciality);
        setResult(RESULT_OK,i);
        startActivity(i);
        finish();
    }*/
}