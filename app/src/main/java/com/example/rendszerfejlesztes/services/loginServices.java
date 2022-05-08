package com.example.rendszerfejlesztes.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class loginServices {

    public interface VolleyResponseTOKENListener {
        void onError(String message);
        void onResponse(String response);
    }

    public interface VolleyResponseLOGINListener {
        void onError(String message);
        void onResponse(String response);
    }

    static Context context;

    public static String getSpeciality()
    {
        SharedPreferences sharedPref = context.getSharedPreferences("Rendszerfejlesztes", Context.MODE_PRIVATE);

        return sharedPref.getString("role", "none");
    }

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

    private void writeToSP(Integer id, String data) {
        SharedPreferences sharedPref = context.getSharedPreferences("Rendszerfejlesztes", Context.MODE_PRIVATE);
        sharedPref.edit().putInt("id", id).commit();
        sharedPref.edit().putInt("token", Integer.parseInt(data.split(":")[0])).commit();
        sharedPref.edit().putString("role", data.split(":")[1]).commit();
    }

    public void checkToken(VolleyResponseTOKENListener volleyResponseTOKENListener) {

        String post_url = "http://kisziftp.tplinkdns.com/api/User/token";

        JSONObject postData = new JSONObject();
        String[] data = readFromSP().split(":");
        try {
            postData.put("id", Integer.parseInt(data[0]));
            postData.put("token", Integer.parseInt(data[1]));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("response")==1)
                        volleyResponseTOKENListener.onResponse("SUCCESS");
                    else if (response.getInt("response")==0)
                        volleyResponseTOKENListener.onResponse("FAIL");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseTOKENListener.onError("Token check failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }


    public void checkLogin(String username, String password, VolleyResponseLOGINListener volleyResponseLOGINListener) {

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
                    if (response.getString("data")=="0")
                        volleyResponseLOGINListener.onResponse("FAIL");
                    else {
                        writeToSP(response.getInt("id"), response.getString("data"));
                        volleyResponseLOGINListener.onResponse("SUCCESS");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseLOGINListener.onError("Check login failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }



}