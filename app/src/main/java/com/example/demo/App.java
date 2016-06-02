package com.example.demo;

import android.app.Application;

import com.example.demo.volley.VolleyHelper;

/**
 * Created by guozhk on 16-6-2.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.initialization(this);
    }
}
