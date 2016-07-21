package com.example.demo.volley1;

import android.os.SystemClock;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.demo.volley1.part.FilePart;
import com.example.demo.volley1.part.StringPart;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by guozhk on 16-7-21.
 */
public class MultiPartRequest<T> extends Request<T> {


    private Response.Listener<T> mListener;

    private MultiParams mMultipartEntity;

    private HttpCallback mHttpCallback;

    public MultiPartRequest(int method, String url, Response.ErrorListener errorListener, final HttpCallback httpResult) {
        super(method, url, errorListener);

        this.mListener = responseListener(httpResult);
        mHttpCallback = httpResult;
        mMultipartEntity = new MultiParams();
        mMultipartEntity.setListener(new MultiParams.ProgressListener() {
            long time = SystemClock.uptimeMillis();
            long count = -1;

            @Override
            public void transferred(long num) {
                if (count == -1) {
                    count = mMultipartEntity.getContentLength();
                }
                // LogUtils.d("bacy", "post->" + count + ",num->" + num);
                long thisTime = SystemClock.uptimeMillis();
                if (thisTime - time >= 100 || count == num) {
                    time = thisTime;
                    httpResult.onLoading(count, num);
                }
            }
        });

        setRetryPolicy(new DefaultRetryPolicy(VolleyContanse.TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return super.getHeaders();
    }

    public static String getProtocolCharset() {
        return VolleyContanse.PROTOCOL_CHARSET;
    }

    @Override
    public String getBodyContentType() {
        return "multipart/form-data; boundary=\"" + VolleyContanse.boundary + '"';
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return super.getBody();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return (Response<T>) Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    public void cancel() {
        super.cancel();
        if (mHttpCallback != null) {
            mHttpCallback.onCancelled();
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    public void addPart(String key, String value) {
        StringPart part = new StringPart(key, value, VolleyContanse.PROTOCOL_CHARSET);
        mMultipartEntity.addPart(part);
    }

    public void addPart(String key, File file) {
        String fileType =  FileMimeType.getMimeType(file);
        FilePart part = new FilePart(key, file, null, fileType);
        mMultipartEntity.addPart(part);
    }


    public MultiParams getmMultipartEntity() {
        return mMultipartEntity;
    }


    protected Response.Listener<T> responseListener(final HttpCallback httpResult) {
        return new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                httpResult.onResult(response);
            }
        };
    }


}
