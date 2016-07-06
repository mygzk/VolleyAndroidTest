package com.example.demo.volley;

import com.android.volley.VolleyError;

public interface RequestListener  {

    /** 成功 */
    void requestSuccess(String result);

    /** 错误 */
     void requestError(VolleyError e);
}
