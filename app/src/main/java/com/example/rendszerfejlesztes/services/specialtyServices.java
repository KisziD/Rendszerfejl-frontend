package com.example.rendszerfejlesztes.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class specialtyServices {

    public interface VolleyResponsePOSTListener {
        void onError(String message);
        void onResponse(String message);
    }

    static Context context;

    public final static String SPEC_POST = "http://kisziftp.tplinkdns.com/api/Speciality/add";

    public void addSpeciality(String spec, String cat, specialtyServices.VolleyResponsePOSTListener volleyResponsePOSTListener) {

        String post_url = SPEC_POST;

        JSONObject postData = new JSONObject();
        try {
            postData.put("name", spec);
            postData.put("categoryname",cat);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("response")==1)
                        volleyResponsePOSTListener.onResponse("SUCCESS");
                    else if (response.getInt("response")==0)
                        volleyResponsePOSTListener.onResponse("FAIL");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponsePOSTListener.onError("Speciality addition failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }
}