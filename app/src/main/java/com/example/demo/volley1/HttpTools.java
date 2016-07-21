package com.example.demo.volley1;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guozhk on 16-7-21.
 */
public class HttpTools {
    private static HttpTools mHttpTools;
    private Context mContext;
    private static RequestQueue sRequestQueue;
    private static RequestQueue sDownloadQueue;

    public static synchronized HttpTools initHttpTools(Context context) {
        if (mHttpTools == null) {
            mHttpTools = new HttpTools(context);
        }
        return mHttpTools;
    }

    private HttpTools(Context context) {
        mContext = context.getApplicationContext();
        init(mContext);
    }

    public static void init(Context context) {
        if (sRequestQueue == null) {
            sRequestQueue = Volley.newRequestQueue(context.getApplicationContext(), new MyHurlStack());
        }
    }

    public static void stop() {
        if (sRequestQueue != null) {
            sRequestQueue.stop();
            sRequestQueue = null;
        }
    }


    public void get(String url, final HttpCallback httpResult) {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        httpResult.onResult(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        httpResult.onError(error);
                    }
                }) {
            @Override
            public void cancel() {
                super.cancel();
                httpResult.onCancelled();
            }
        };

        stringRequest.setTag(this);
        sRequestQueue.add(stringRequest);

    }


    /**
     * post 请求
     *
     * @param url
     * @param params
     * @param httpResult
     */
    public void post(final String url, final Map<String, Object> params, final HttpCallback httpResult) {
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.url = url;
        requestInfo.putAllParams(params);
        post(requestInfo, httpResult);
    }

    /**
     * post 请求
     *
     * @param requestInfo
     * @param httpResult
     */
    public void post(final RequestInfo requestInfo, final HttpCallback httpResult) {
        if (sRequestQueue == null) {
            return;
        }
        final String url = requestInfo.getUrl();
        if (TextUtils.isEmpty(url)) {
            if (httpResult != null) {
                httpResult.onStart();
                httpResult.onError(new Exception("url can not be empty!"));
                httpResult.onFinish();
            }
            return;
        }
        final Map<String, String> paramsMap = requestInfo.getParams();
        final Map<String, File> fileParams = requestInfo.getFileParams();

        if (httpResult != null) {
            httpResult.onStart();
        }
        MultiPartRequest<String> request = new MultiPartRequest<String>(Request.Method.POST,
                url, errorListener(httpResult), httpResult) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Charset", "UTF-8");
                headers.putAll(requestInfo.getHeaders());
                return headers;
            }

        };

        if (paramsMap != null && paramsMap.size() != 0) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                request.addPart(entry.getKey(), entry.getValue());
            }
        }
        if (fileParams != null && fileParams.size() != 0) {
            for (Map.Entry<String, File> entry : fileParams.entrySet()) {
                String key = entry.getKey();
                int index = key.indexOf(requestInfo.boundary);
                if (index > 0) {
                    key = key.substring(0, index);
                }
                request.addPart(key, entry.getValue());
            }
        }
        request.setTag(this);
        sRequestQueue.add(request);
    }

    public void cancelAllRequest() {
        if (sRequestQueue != null) {
            sRequestQueue.cancelAll(this);
        }
    }

    public void quitDownloadQueue() {
        if (sDownloadQueue != null) {
            sDownloadQueue.stop();
            sDownloadQueue = null;
        }
    }


    protected Response.ErrorListener errorListener(final HttpCallback httpResult) {

        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                httpResult.onError(error);
            }
        };
    }


}
