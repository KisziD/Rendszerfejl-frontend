package com.example.rendszerfejlesztes.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rendszerfejlesztes.models.DeviceModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class deviceServices{

    public interface VolleyResponseGETDEVICESListener {
        void onError(String message);
        void onResponse(ArrayList<DeviceModel> deviceModels);
    }

    public interface VolleyResponseGETDEVICEListener {
        void onError(String message);
        void onResponse(DeviceModel dm);
    }

    public interface VolleyResponsePOSTListener {
        void onError(String message);
        void onResponse(String message);
    }

    public final static String DEVICES = "http://kisziftp.tplinkdns.com/api/Device";
    public final static String DEVICE_ID = "http://kisziftp.tplinkdns.com/api/Device/all/";
    public final static String DEVICE_POST = "http://kisziftp.tplinkdns.com/api/Device/add";
    static Context context;

    public deviceServices(Context context) {
        this.context = context;
    }


    public void addDevice(String cate, String name, String place, String desc,VolleyResponsePOSTListener volleyResponsePOSTListener) {

        String post_url = DEVICE_POST;

        JSONObject postData = new JSONObject();
        try {
            postData.put("name", name);
            postData.put("categoryName", cate);
            postData.put("location", place);
            postData.put("description", desc);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyResponsePOSTListener.onResponse("Device added");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponsePOSTListener.onError("Device addition failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }


    public static void getDevice(Integer devID, final VolleyResponseGETDEVICEListener volleyResponseGETDEVICEListener) {
        String url = DEVICE_ID + devID;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                DeviceModel dm = new DeviceModel();
                try {
                        dm.setId(response.getInt("id"));
                        dm.setName(response.getString("name"));
                        dm.setCategoryName(response.getString("categoryName"));
                        dm.setLocation(response.getString("location"));
                        dm.setDesc(response.getString("description"));
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                volleyResponseGETDEVICEListener.onResponse(dm);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                volleyResponseGETDEVICEListener.onError("Getting device failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }


    public static void getDevices(final VolleyResponseGETDEVICESListener volleyResponseGETDEVICESListener) {

        String url = DEVICES;
        ArrayList<DeviceModel> list = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        DeviceModel dm = new DeviceModel();
                        JSONObject deviceInfo = response.getJSONObject(i);
                        dm.setId(deviceInfo.getInt("id"));
                        dm.setName(deviceInfo.getString("name"));
                        dm.setCategoryName(deviceInfo.getString("categoryName"));
                        dm.setLocation(deviceInfo.getString("location"));
                        dm.setDesc(deviceInfo.getString("description"));
                        list.add(dm);
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                volleyResponseGETDEVICESListener.onResponse(list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                volleyResponseGETDEVICESListener.onError("Getting devices failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }
}