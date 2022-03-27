package com.example.rendszerfejlesztes.services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

    public interface VolleyResponseListener
    {
        void onError(String message);
        void onResponse(ArrayList<DeviceModel> deviceModels);
    }

    public interface VolleyResponseGETDEVICEListener
    {
        void onError(String message);
        void onResponse(DeviceModel dm);
    }

    public interface VolleyResponsePOSTListener
    {
        void onError(String message);
        void onResponse(String message);
    }

    public final static String DEVICES = "http://kisziftp.tplinkdns.com/api/Device";
    public final static String DEVICE_POST = "http://kisziftp.tplinkdns.com/api/Device/add";
    public final static String DEVICE_ID = "http://kisziftp.tplinkdns.com/api/Device/all/";
    static Context context;

    public deviceServices(Context context) {
        this.context = context;
    }

    public void addDevice(Integer cate, String name, String place, String desc,VolleyResponsePOSTListener volleyResponsePOSTListener) {

        String post_url = DEVICE_POST;

        JSONObject postData = new JSONObject();
        try {
            postData.put("name", name);
            postData.put("categoryID", cate);
            postData.put("location", place);
            postData.put("description", desc);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyResponsePOSTListener.onResponse("Device Added");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponsePOSTListener.onError(error.toString());
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);

    }

    public static void getDevice(Integer devID, final VolleyResponseGETDEVICEListener volleyResponseGETDEVICEListener)
    {
        String url = DEVICE_ID + devID;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                DeviceModel dm = new DeviceModel();
                try {
                        dm.setId(response.getInt("id"));
                        dm.setName(response.getString("name"));
                        dm.setCatID(response.getInt("categoryID"));
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
                volleyResponseGETDEVICEListener.onError(error.toString());
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }


    public static void getDevices(final VolleyResponseListener volleyResponseListener)
    {
        String url = DEVICES;
        ArrayList<DeviceModel> list = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Integer id = 0;
                String name ="";
                Integer catID = 0;
                String location = "";
                String desc = "";
                try {
                    for (int i = 0; i < response.length(); i++) {
                        DeviceModel dm = new DeviceModel();
                        JSONObject deviceInfo = response.getJSONObject(i);
                        dm.setId(deviceInfo.getInt("id"));
                        dm.setName(deviceInfo.getString("name"));
                        dm.setCatID(deviceInfo.getInt("categoryID"));
                        dm.setLocation(deviceInfo.getString("location"));
                        dm.setDesc(deviceInfo.getString("description"));
                        list.add(dm);
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                volleyResponseListener.onError(error.toString());
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }

}



