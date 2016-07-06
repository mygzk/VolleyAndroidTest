package com.example.demo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demo.volley.IRequest;
import com.example.demo.volley.RequestListener;
import com.example.demo.volley.RequestParams;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.xutils.common.Callback;
import org.xutils.x;

public class MainActivity extends Activity implements View.OnClickListener {
    private String TAG = MainActivity.class.getSimpleName();
    private String urlGet = "http://172.16.0.32/User.svc/ShowName?name=gzk";
    private String urlPost = "http://172.16.0.32:81/User.svc";

    private TextView tvResult;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        params.addBodyParameter("name", "gzk");
        params.addBodyParameter("name1", "lxx");
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.demo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.demo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
