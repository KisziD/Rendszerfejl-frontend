package com.example.rendszerfejlesztes.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rendszerfejlesztes.models.CategoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class categoryServices {

    public interface VolleyResponseCATListener
    {
        void onResponse(List<String> ls);
        void onError(String message);
    }

    public interface VolleyResponsePOSTListener
    {
        void onResponse(String message);
        void onError(String message);
    }

    public final static String CAT_POST = "http://kisziftp.tplinkdns.com/api/Category/add";
    public final static String CAT_NAME = "http://kisziftp.tplinkdns.com/api/Category/names";
    static Context context;



    public void addCategory(String name, String parent, categoryServices.VolleyResponsePOSTListener volleyResponsePOSTListener) {

        String post_url = CAT_POST;

        JSONObject postData = new JSONObject();
        try {
            postData.put("name", name);
            postData.put("parent", parent);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyResponsePOSTListener.onResponse("Category Added");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponsePOSTListener.onError("Error");
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
                        for (int i = 0; i <response.length(); i++)
                        {
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
                volleyResponseCATListener.onError(error.toString());
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }
}
