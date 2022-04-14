package com.example.rendszerfejlesztes.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class specialityServices {

    public interface VolleyResponsePOSTListener {
        void onError(String message);
        void onResponse(String message);
    }

    public interface VolleyResponseGETSPECListener {
        void onError(String message);
        void onResponse(List<String> ls);
    }

    static Context context;

    public final static String SPEC_POST = "http://kisziftp.tplinkdns.com/api/Speciality/add";
    public final static String SPEC_GET = "https://kisziftp.tplinkdns.com/api/Speciality/names";


    public void addSpeciality(String spec, String cat, specialityServices.VolleyResponsePOSTListener volleyResponsePOSTListener) {

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

    public static void getSpeciality(final VolleyResponseGETSPECListener volleyResponseGETSPECListener)
    {
        String get_url = SPEC_GET;
        List<String> list = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, get_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            list.add(obj.getString("name"));
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseGETSPECListener.onResponse(list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               volleyResponseGETSPECListener.onError("Getting specialities failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }
}