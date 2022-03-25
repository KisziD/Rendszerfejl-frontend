package com.example.rendszerfejlesztes.services;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.rendszerfejlesztes.models.DeviceModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class deviceServices{

    public void addDevice(String name, String place, String desc)
    {

    }

    public static final String DEVICE_ID = "http://kisziftp.tplinkdns.com/api/Device/0";

    Context context;

    public deviceServices(Context context) {
        this.context = context;
    }


    public void getDevice(String devID)
    {
        String url = DEVICE_ID + devID;
        ArrayList<DeviceModel> list = new ArrayList<DeviceModel>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Integer id = 0;
                String name ="";
                Integer catID = 0;
                String location = "";
                String desc = "";
                try {
                    for (int i = 0; i < response.length(); i++)
                    {
                        DeviceModel dm = new DeviceModel();
                        JSONObject deviceInfo = response.getJSONObject(i);
                        dm.setId(deviceInfo.getInt("id"));
                        dm.setName(deviceInfo.getString("name"));
                        dm.setCatID(deviceInfo.getInt("categoryID"));
                        dm.setLocation(deviceInfo.getString("location"));
                        dm.setDesc(deviceInfo.getString("description"));
                        list.add(dm);
                    }
                }catch (JSONException e)
                {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

            }
        });

       singleton.getInstance(context).addToRequestQueue(request);

        return list;

    }

}

