package com.example.demo.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by guozhk on 16-6-2.
 */
public class VolleyHelper {
    private static volatile VolleyHelper mVolleyHelper;
    private static volatile RequestQueue mRequestQueue;

    private VolleyHelper(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleyHelper initialization(Context context) {
        if (mVolleyHelper == null) {
            synchronized (VolleyHelper.class) {
                mVolleyHelper = new VolleyHelper(context);
            }
        }
        return mVolleyHelper;
    }

    public static void startRequest(Request request) {
        if (mRequestQueue == null) {
            return;
        }

        mRequestQueue.add(request);
    }





}
