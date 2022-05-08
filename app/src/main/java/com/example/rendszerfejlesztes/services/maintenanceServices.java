package com.example.rendszerfejlesztes.services;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rendszerfejlesztes.models.TaskConModel;
import com.example.rendszerfejlesztes.models.TaskModel;

import java.util.ArrayList;

public class maintenanceServices {

    public interface VolleyResponseGETTASKListener {
        void onResponse(TaskModel tm);
        void onError(String message);
    }
    public interface VolleyResponseGETTASKSListener {
        void onError(String message);
        void onResponse(ArrayList<TaskModel> taskModels);
    }
    public interface VolleyResponsePOSTListener {
        void onError(String message);
        void onResponse(String message);
    }

    public interface VolleyResponseGETTASKCONNECTIONListener {
        void onError(String message);
        void onResponse(ArrayList<TaskConModel> taskConModels);
    }

    public interface VolleyResponseSTATEPOSTListener {
        void onError(String message);
        void onResponse(String message);
    }

    public final static String MAINT_POST = "http://kisziftp.tplinkdns.com/api/Maintenance/add";
    public final static String TASK_GET = "http://kisziftp.tplinkdns.com/api/Maintenance/all/";
    public final static String TASKS_GET = "http://kisziftp.tplinkdns.com/api/Maintenance";
    public final static String TASKS_CON = "http://kisziftp.tplinkdns.com/api/Task";
    public final static String MAINT_CHANGE = "http://kisziftp.tplinkdns.com/api/maintenance/state";
    public final static String TASK_ID = "http://kisziftp.tplinkdns.com/api/task/add";
    public final static String TASK_SPEC = "http://kisziftp.tplinkdns.com/api/specialist/tasks/";
    static Context context;

    public void addCategory(String name, String justification, categoryServices.VolleyResponsePOSTListener volleyResponsePOSTListener) {

        String post_url = MAINT_POST;
        Log.d(" url", post_url);

        JSONObject postData = new JSONObject();
        try {
            postData.put("name", name.split(": ")[0]);
            postData.put("justification", justification);
            postData.put("location", name.split(": ")[1]);
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

    public static void changeState(Integer maintID, Integer state, Integer specID, String reason, final maintenanceServices.VolleyResponseSTATEPOSTListener volleyResponseSTATEPOSTListener) {

        String post_url = MAINT_CHANGE;
        Log.d(" url", post_url);

        JSONObject postData = new JSONObject();
        try {
            postData.put("id", maintID);
            postData.put("state", state);
            Log.d(" post", state.toString());
            postData.put("specialistid", specID);
            postData.put("reason", reason);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyResponseSTATEPOSTListener.onResponse("Maintenance state changed");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseSTATEPOSTListener.onError("Maintenance state changing is failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }

    public static void getTask(Integer devID, VolleyResponseGETTASKListener volleyResponseGETTASKListener){
            String get_url = TASK_GET + devID;

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, get_url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    TaskModel tm = new TaskModel();
                    try
                    {
                        tm.setId(response.getInt("id"));
                        tm.setDev_name(response.getString("device"));
                        tm.setDate(response.getString("date"));
                        tm.setState(response.getString("state"));
                        tm.setInstructions(response.getString("instructions"));
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                    volleyResponseGETTASKListener.onResponse(tm);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    volleyResponseGETTASKListener.onError("Getting task failed");
                }
            });
            SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }

    public static void getTasks( final VolleyResponseGETTASKSListener volleyResponseGETTASKSListener)
    {
        String get_url = TASKS_GET;
        ArrayList<TaskModel> list = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, get_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        TaskModel tm = new TaskModel();
                        JSONObject taskInfo = response.getJSONObject(i);
                        tm.setId(taskInfo.getInt("id"));
                        tm.setDev_name(taskInfo.getString("device"));
                        tm.setDate(taskInfo.getString("date"));
                        tm.setState(taskInfo.getString("state"));
                        tm.setInstructions(taskInfo.getString("instructions"));
                        list.add(tm);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseGETTASKSListener.onResponse(list);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseGETTASKSListener.onError("Getting tasks failed");
            }
        });

        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }

    public static void getTasks2(Integer ID, final VolleyResponseGETTASKSListener volleyResponseGETTASKSListener)
    {
        String get_url = TASK_SPEC + ID;
        ArrayList<TaskModel> list = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, get_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        TaskModel tm = new TaskModel();
                        JSONObject taskInfo = response.getJSONObject(i);
                        tm.setId(taskInfo.getInt("id"));
                        tm.setDev_name(taskInfo.getString("device"));
                        tm.setDate(taskInfo.getString("date"));
                        tm.setState(taskInfo.getString("state"));
                        tm.setInstructions(taskInfo.getString("instructions"));
                        list.add(tm);
                        Log.d("tm", list.size() + " ");
                    }
                    volleyResponseGETTASKSListener.onResponse(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseGETTASKSListener.onError("Getting tasks failed");
            }
        });

        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }


    public static void postTaskID(Integer taskID, String specID , final maintenanceServices.VolleyResponsePOSTListener volleyResponsePOSTListener)
    {
        String post_url = TASK_ID;
        JSONObject postData = new JSONObject();
        try{
            postData.put("MaintenanceID", taskID);
            postData.put("SpecialistID", Integer.parseInt(specID.split(": ")[0]));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, post_url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyResponsePOSTListener.onResponse("OK");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponsePOSTListener.onError("Error");

            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }

    public static void getTaskConnection(final maintenanceServices.VolleyResponseGETTASKCONNECTIONListener volleyResponseGETTASKCONNECTIONListener)
    {

        String url = TASKS_CON;
        ArrayList<TaskConModel> list = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        TaskConModel t = new TaskConModel();
                        JSONObject taskInfo = response.getJSONObject(i);
                        t.setId(taskInfo.getInt("id"));
                        t.setMaintenanceID(taskInfo.getInt("maintenanceID"));
                        t.setSpecialistID(taskInfo.getInt("specialistID"));
                        list.add(t);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseGETTASKCONNECTIONListener.onResponse(list);
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseGETTASKCONNECTIONListener.onError("Getting tasks failed");
            }
        });
        SingletonRequestQueue.getInstance(context).addToRequestQueue(request);
    }

}