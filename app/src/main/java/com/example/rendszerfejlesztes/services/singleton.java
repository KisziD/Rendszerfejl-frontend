package com.example.rendszerfejlesztes.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class singleton {
    private static singleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private singleton(Context context)
    {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized singleton getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new singleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue()
    {
        if (requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }
}
