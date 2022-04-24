package com.example.rendszerfejlesztes.services;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rendszerfejlesztes.device.maintenanceCreator_activity;

import java.util.List;

public class maintenanceServices {

    public interface VolleyResponsePOSTListener {
        void onError(String message);
        void onResponse(String message);
    }

    public interface VolleyResponseMainTListener {
        void onError(String message);
        void onResponse(List<String> ls);
    }

    public final static String MAINT_POST = "http://kisziftp.tplinkdns.com/api/Maintenance/add";
    static Context context;

    public void addCategory(String deviceID, String date, String justification, categoryServices.VolleyResponsePOSTListener volleyResponsePOSTListener) {

        String post_url = MAINT_POST;

        JSONObject postData = new JSONObject();
        try {
            postData.put("deviceID", deviceID);
            postData.put("date", date);
            postData.put("justification", justification);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyResponsePOSTListener.onResponse("Maintenance added");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponsePOSTListener.onError("Maintenance addition failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }
}
