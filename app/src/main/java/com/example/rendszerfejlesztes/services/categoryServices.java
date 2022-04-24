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

public class categoryServices {

    public interface VolleyResponsePOSTListener {
        void onError(String message);
        void onResponse(String message);
    }

    public interface VolleyResponseCATListener {
        void onError(String message);
        void onResponse(List<String> ls);
    }

    public final static String CAT_POST = "http://kisziftp.tplinkdns.com/api/Category/add";
    public final static String CAT_NAME = "http://kisziftp.tplinkdns.com/api/Category/names";
    static Context context;


    public void addCategory(String name, String norm_h, String period, String instructions, String parent, categoryServices.VolleyResponsePOSTListener volleyResponsePOSTListener) {

        String post_url = CAT_POST;

        JSONObject postData = new JSONObject();
        try {
            postData.put("name", name);
            postData.put("norm_h", norm_h);
            postData.put("period", period);
            postData.put("instructions", instructions);
            postData.put("parent", parent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyResponsePOSTListener.onResponse("Category added");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponsePOSTListener.onError("Category addition failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }


    public static void getCategoryNames(final VolleyResponseCATListener volleyResponseCATListener)
    {
        String url = CAT_NAME;
        List<String> list = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                        for (int i = 0; i <response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            list.add(obj.getString("name"));
                        }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                volleyResponseCATListener.onResponse(list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                volleyResponseCATListener.onError("Getting categories failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }
}