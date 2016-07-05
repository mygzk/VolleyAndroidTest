package com.example.demo.volley;

import com.android.volley.VolleyError;

/**
 * 小袁
 * Created by Administrator on 2015/3/11.
 */
public interface RequestJsonListener<T> {

    /**
     * requestSuccess
     * @param result
     */
    public void requestSuccess(T result);

    /**
     * 错误
     */
    public void requestError(VolleyError e);
}
