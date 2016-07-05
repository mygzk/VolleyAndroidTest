package com.example.demo;

import android.app.Application;
import android.content.Context;

/**
 * Created by guozhk on 16-6-2.
 */
public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }

    public static Context getContext() {
        return mContext;
    }
}
