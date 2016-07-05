package com.example.demo.volley;

import com.android.volley.VolleyError;


public interface RequestJsonListener<T> {

    /**
     * requestSuccess
     * @param result
     */
     void requestSuccess(T result);

    /**
     * 错误
     */
     void requestError(VolleyError e);
}
