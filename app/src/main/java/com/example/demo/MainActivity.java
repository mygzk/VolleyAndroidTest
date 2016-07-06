package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demo.volley.IRequest;
import com.example.demo.volley.RequestListener;
import com.example.demo.volley.RequestParams;

import org.xutils.common.Callback;
import org.xutils.x;

public class MainActivity extends Activity implements View.OnClickListener {
    private String TAG = MainActivity.class.getSimpleName();
    private String urlGet = "http://172.16.0.32/User.svc/ShowName?name=gzk";
    private String urlPost = "http://172.16.0.32:81/User.svc";

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x.Ext.init(this.getApplication());//Xutils初始化

        findViewById(R.id.btn_volleyget).setOnClickListener(this);
        findViewById(R.id.btn_volleypost).setOnClickListener(this);
        findViewById(R.id.btn_xutilget).setOnClickListener(this);
        findViewById(R.id.btn_xutilpost).setOnClickListener(this);
        findViewById(R.id.btn_post4).setOnClickListener(this);
        tvResult = (TextView) findViewById(R.id.result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_volleyget:
                volleyGetTest();
                break;
            case R.id.btn_volleypost:
                volleyPostTest();
                break;
            case R.id.btn_xutilget:
                xutilGetTest();
                break;
            case R.id.btn_xutilpost:
                xutilPostTest();
                break;
        }
    }

    private void volleyGetTest() {
        IRequest.get(this, urlGet, new RequestListener() {
            @Override
            public void requestSuccess(String result) {
                Log.e(TAG, "volley get result:" + result);
                tvResult.setText(tvResult.getText() + "\nvolley" + result);
            }

            @Override
            public void requestError(VolleyError e) {
                Log.e(TAG, "volley get result:" + e.toString());
                tvResult.setText(tvResult.getText() + "\nvolley get请求异常");
                e.printStackTrace();

            }
        });
    }

    private void volleyPostTest() {
        RequestParams params = new RequestParams();
        params.put("name", "gzk_post");
        params.put("com", "zayh");
        IRequest.post(this, urlPost, params, new RequestListener() {
            @Override
            public void requestSuccess(String result) {
                Log.e(TAG, "volley post result:" + result);
                tvResult.setText(tvResult.getText() + "\nvolley" + result);
            }

            @Override
            public void requestError(VolleyError e) {
                Log.e(TAG, "post result:" + e.toString());
                tvResult.setText(tvResult.getText() + "\nvolley post请求异常");
            }
        });
    }

    private void xutilGetTest() {
        org.xutils.http.RequestParams params = new org.xutils.http.RequestParams(urlGet);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "xutil get result:" + result);
                tvResult.setText(tvResult.getText() + "\n xutil get:" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "get result:" + ex.toString());
                tvResult.setText(tvResult.getText() + "\n xutil get请求异常");
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "xutul get onCancelled: CancelledException:" + cex.toString());
                cex.printStackTrace();
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "xutul get finish");
            }
        });
    }

    private void xutilPostTest() {
        org.xutils.http.RequestParams params = new org.xutils.http.RequestParams(urlPost);
        params.addBodyParameter("name","gzk");
        params.addBodyParameter("name1","lxx");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "xutil post result:" + result);
                tvResult.setText(tvResult.getText() + "\n xutil post:" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "xutil post result:" + ex.toString());
                tvResult.setText(tvResult.getText() + "\n xutil post请求异常");
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "xutul post onCancelled: CancelledException:" + cex.toString());
                cex.printStackTrace();
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "xutul post finish");
            }
        });
    }

}
