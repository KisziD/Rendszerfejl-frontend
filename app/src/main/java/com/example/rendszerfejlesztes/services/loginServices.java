package com.example.rendszerfejlesztes.services;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.rendszerfejlesztes.interfaces.login_activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;


public class loginServices {

    public static boolean checkToken() {
        //folyamatban...
        return true;
    }

    public static class token extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL("kisziftp.tplinkdns.com/api/User");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);
                    int data = isw.read();
                    while (data != -1) {
                        result += (char) data;
                        data = isw.read();
                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            String my_users = "";
            try {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray jsonArray1 = jsonObject.getJSONArray("user");
                JSONObject jsonObject1 = jsonArray1.getJSONObject(1);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("username");
                //String pwd = jsonObject1.getString("password");
                my_users = "User ID: " + id + "\n" + "Name: " + name + "\n\n";
                System.out.println(my_users);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    public static class connectToDB extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL("kisziftp.tplinkdns.com/api/User");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);
                    int data = isw.read();
                    while (data != -1) {
                        result += (char) data;
                        data = isw.read();
                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            String my_users = "";
            try {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONArray jsonArray1 = jsonObject.getJSONArray("user");
                for(int index_no=0;index_no<jsonArray1.length();index_no++) {
                    JSONObject jsonObject1 = jsonArray1.getJSONObject(index_no);
                    String id = jsonObject1.getString("id");
                    String name = jsonObject1.getString("username");
                    //String pwd = jsonObject1.getString("password");
                    my_users = "User ID: " + id + "\n" + "Name: " + name + "\n\n";
                }
                //System.out.println(my_users);
            } catch (JSONException e) {
                e.printStackTrace();
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
