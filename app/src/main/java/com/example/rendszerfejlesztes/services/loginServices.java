package com.example.rendszerfejlesztes.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rendszerfejlesztes.models.DeviceModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class loginServices {

    public interface VolleyResponseTokenListener
    {
        void onError(String message);
        void onResponse(String response);
    }

    public interface VolleyResponseLoginListener
    {
        void onError(String message);
        void onResponse(String response);
    }

    static Context context;

    public loginServices(Context context) {
        this.context = context;
    }

    private String readFromSP() {
        SharedPreferences sharedPref = context.getSharedPreferences("Rendszerfejlesztes", Context.MODE_PRIVATE);
        if(sharedPref.contains("id")) {
            int id = sharedPref.getInt("id", 0);
            int token = sharedPref.getInt("token", 0);
            return id+":"+token;
        } else {
            return "-1:0";
        }
    }

    private void writeToSP(Integer id, Integer token) {
        SharedPreferences sharedPref = context.getSharedPreferences("Rendszerfejlesztes", Context.MODE_PRIVATE);
        sharedPref.edit().putInt("id", id).commit();
        sharedPref.edit().putInt("token", token).commit();
    }

    public void checkToken(VolleyResponseTokenListener volleyResponseTokenListener) {

        String post_url = "http://kisziftp.tplinkdns.com/api/User/token";

        JSONObject postData = new JSONObject();
        String[] data = readFromSP().split(":");
        try {
            postData.put("id", Integer.parseInt(data[0]));
            postData.put("token", Integer.parseInt(data[1]));
            //Log.d("Check: ", data[0] + ":" + data[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("response")==1)
                        volleyResponseTokenListener.onResponse("SUCCESS");
                    else if (response.getInt("response")==0)
                        volleyResponseTokenListener.onResponse("FAIL");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseTokenListener.onError(error.toString());
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);

    }

    public void checkLogin(String username, String password, VolleyResponseLoginListener volleyResponseLoginListener) {

        String post_url = "http://kisziftp.tplinkdns.com/api/User/login";

        JSONObject postData = new JSONObject();
        try {
            postData.put("username", username);
            postData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("token")==0)
                        volleyResponseLoginListener.onResponse("FAIL");
                    else {
                        writeToSP(response.getInt("id"), response.getInt("token"));
                        //Log.d("Login:", readFromSP());
                        volleyResponseLoginListener.onResponse("SUCCESS");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseLoginListener.onError(error.toString());
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);

    }
}
