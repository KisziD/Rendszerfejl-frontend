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

public class specialistServices {

    public interface VolleyResponsePOSTListener {
        void onError(String message);
        void onResponse(String message);
    }

    public interface VolleyResponseGETSPECListener {
        void onError(String message);
        void onResponse(List<String> ls);
    }

    public final static String SPEC_ADD = "https://kisziftp.tplinkdns.com/api/Specialist/add";
    public final static String SPEC_GET = "https://kisziftp.tplinkdns.com/api/Specialist/names";

    static Context context;

    public void addSpecialist(String name, specialistServices.VolleyResponsePOSTListener volleyResponsePOSTListener)
    {
        String post_url = SPEC_ADD;
        JSONObject postData = new JSONObject();
        try{
            postData.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyResponsePOSTListener.onResponse("Specialist added");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponsePOSTListener.onError("Specialist addition failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }

    public static void getSpecialist(final specialistServices.VolleyResponseGETSPECListener volleyResponseGETSPECListener)
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

    public void addSpecialistplus(String name,String spec, specialistServices.VolleyResponsePOSTListener volleyResponsePOSTListener)
    {
        String post_url = SPEC_ADD;
        JSONObject postData = new JSONObject();
        try{
            postData.put("name", name);
            postData.put("qualification", spec);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyResponsePOSTListener.onResponse("Assigment added");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponsePOSTListener.onError("Assigment failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }
}
