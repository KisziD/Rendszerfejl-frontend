package com.example.rendszerfejlesztes.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rendszerfejlesztes.models.SpecialistModel;

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

        void onResponse(ArrayList<SpecialistModel> specialistModels);
    }

    public final static String SPEC_ADD = "http://kisziftp.tplinkdns.com/api/Specialist/add";
    public final static String SPEC_GET = "http://kisziftp.tplinkdns.com/api/task/esp/";

    static Context context;



   public static void getSpecialist(Integer taskID, final specialistServices.VolleyResponseGETSPECListener volleyResponseGETSPECListener)
    {
        String get_url = SPEC_GET + taskID ;
        Log.d("url", get_url);
        ArrayList<SpecialistModel> list = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, get_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        list.add(new SpecialistModel(obj.getInt("id"), obj.getString("name")));
                    }
                }catch (JSONException e) {
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

    public void addSpecialist(String name, String username, String password, String role, String qualific, specialistServices.VolleyResponsePOSTListener volleyResponsePOSTListener)
    {
        String post_url = SPEC_ADD;
        JSONObject postData = new JSONObject();
        try{
            postData.put("name", name);
            postData.put("username", username);
            postData.put("password", password);
            postData.put("role", role);
            postData.put("SpecialityName", qualific);
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
                volleyResponsePOSTListener.onError("Addition failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }
}