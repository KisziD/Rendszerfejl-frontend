package com.example.rendszerfejlesztes.services;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import javax.net.ssl.HttpsURLConnection;


public class loginServices {

    public static boolean checkToken() {
        try {
            restApiService.token();
            return true;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            System.out.println("ERROR");
            return false;
        }
    }


    public static class restApiService {
        /*final static String url = "";
        final static String id = "1";
        final static String token = "1136971380";*/
        final static String pwd = "kiszi";

        public static void token() throws IOException, JSONException {
            URL url = new URL("kisziftp.tplinkdns.com/api/User/token");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            Map json=new HashMap();
            json.put("id",new Integer(1));
            json.put("token",new Double(1136971380));
            String jsonInputString = json.toString();
            System.out.println(jsonInputString);
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
        }

        public static void login() throws IOException, JSONException {
            URL url = new URL("kisziftp.tplinkdns.com/api/User/login");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            Map json=new HashMap();
            json.put("id",new Integer(1));
            json.put("username","kiszi");
            int hashedPwd = pwd.hashCode();
            json.put("password", new Integer(hashedPwd));
            String jsonInputString = json.toString();
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
        }

    }


    public static void getData(TextView t, Intent i) {
        Bundle extras = i.getExtras();
        if(extras != null) {
            String u = i.getStringExtra("user");
            String j = i.getStringExtra("job");
            t.setText(u + " (" + j + ")");
        }
    }
}
