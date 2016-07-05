package com.example.demo.volley;

import android.annotation.SuppressLint;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.demo.App;
import com.example.demo.volley.util.JsonUtils;

import java.io.UnsupportedEncodingException;

@SuppressLint("NewApi")
public class RequestManager {
    private static final String charset = "UTF-8";
    public static RequestQueue mRequestQueue = Volley.newRequestQueue(App
            .getContext());

    private RequestManager() {
    }

    /**
     * 返回String
     *
     * @param url      连接
     * @param tag      上下文
     * @param listener 回调
     */
    public static void get(String url, Object tag, RequestListener listener) {
        ByteArrayRequest request = new ByteArrayRequest(Request.Method.GET,
                url, null, responseListener(listener),
                responseError(listener));
        addRequest(request, tag);
    }

    /**
     * 返回对象
     *
     * @param url      连接
     * @param tag      上下文
     * @param classOfT 类对象
     * @param listener 回调
     */
    public static <T> void get(String url, Object tag, Class<T> classOfT,
                               RequestJsonListener<T> listener) {
        ByteArrayRequest request = new ByteArrayRequest(Request.Method.GET,
                url, null, responseListener(listener, classOfT),
                responseError(listener));
        addRequest(request, tag);
    }

    /**
     * 返回String
     *
     * @param url      接口
     * @param tag      上下文
     * @param params   post需要传的参数
     * @param listener 回调
     */
    public static void post(String url, Object tag, RequestParams params,
                            RequestListener listener) {
        ByteArrayRequest request = new ByteArrayRequest(Request.Method.POST,
                url, params, responseListener(listener),
                responseError(listener));
        addRequest(request, tag);
    }

    /**
     * 返回对象 带进度条
     *
     * @param url      接口
     * @param tag      上下文
     * @param classOfT 类对象
     * @param params   post需要传的参数
     * @param listener 回调
     */
    public static <T> void post(String url, Object tag, Class<T> classOfT,
                                RequestParams params,
                                RequestJsonListener<T> listener) {
        ByteArrayRequest request = new ByteArrayRequest(Request.Method.POST,
                url, params,
                responseListener(listener, classOfT),
                responseError(listener));
        addRequest(request, tag);
    }

    /**
     * 成功消息监听 返回对象
     *
     * @param l
     * @return
     */
    protected static <T> Response.Listener<byte[]> responseListener(
            final RequestJsonListener<T> l, final Class<T> classOfT) {
        return new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] arg0) {
                String data = null;
                try {
                    data = new String(arg0, charset);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                l.requestSuccess(JsonUtils.object(data, classOfT));
            }
        };
    }

    /**
     * 对象返回错误监听
     *
     * @param l 回调
     * @return
     */
    protected static <T> Response.ErrorListener responseError(
            final RequestJsonListener<T> l) {
        return new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError e) {
                l.requestError(e);
            }
        };
    }

    /**
     * 成功消息监听 返回String
     *
     * @param l String 接口
     * @return
     */
    protected static Response.Listener<byte[]> responseListener(
            final RequestListener l) {
        return new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] arg0) {
                String data = null;
                try {
                    data = new String(arg0, charset);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                l.requestSuccess(data);
            }
        };
    }

    /**
     * String 返回错误监听
     *
     * @param l String 接口
     * @return
     */
    protected static Response.ErrorListener responseError(
            final RequestListener l) {
        return new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError e) {
                l.requestError(e);
            }
        };
    }

    public static void addRequest(Request<?> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    /**
     * 当主页面调用协议 在结束该页面调用此方法
     *
     * @param tag
     */
    public static void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
