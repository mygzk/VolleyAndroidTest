package com.example.demo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener {
    private String TAG = MainActivity.class.getSimpleName();
    private String urlGet = "http://172.16.0.32/User.svc/ShowName?name=gzk";
    //private String urlPost = "http://172.16.0.32:7789/MySericeDemo/postMostStr/8";
    private String urlPost = "http://172.16.0.32:7789/MySericeDemo/postMostStr";

    private String urlPost1 = "http://172.16.0.32:81/Service2.svc?wsdl";

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
        findViewById(R.id.btn_result_empty).setOnClickListener(this);
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
            case R.id.btn_result_empty:
                tvResult.setText("结果:");
                break;
        }
    }

    String rootPath = Environment.getExternalStorageDirectory().getPath();
    String f1 = rootPath + File.separator + "1.jpg";
    String f2 = rootPath + File.separator + "2.jpg";
    String f3 = rootPath + File.separator + "3.jpg";
    String f4 = rootPath + File.separator + "4.jpg";
    String f5 = rootPath + File.separator + "5.JPEG";
    String f6 = rootPath + File.separator + "6.jpg";
    File file1 = new File(f1);
    File file2 = new File(f2);
    File file3 = new File(f3);
    File file4 = new File(f4);
    File file5 = new File(f5);
    File file6 = new File(f6);

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
        params.put("com1", "zayh1");
       /* if (file1.exists()) {
            params.put("file1", file1);
        }
        if (file2.exists()) {
            params.put("file2", file2);
        }
        if (file3.exists()) {
            params.put("file3", file3);
        }
        if (file4.exists()) {
            params.put("file4", file4);
        }*/
        if (file5.exists()) {
            params.put("file5", file5);
        }
        if (file6.exists()) {
            params.put("file6", file6);
        }
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
        params.setMultipart(true);
        params.addBodyParameter("name", "gzk");
        params.addBodyParameter("name1", "lxx");
        /*if (file1.exists()) {
            params.addBodyParameter("file1", file1,null);
        }
        if (file2.exists()) {
            params.addBodyParameter("file2", file2,null);
        }*/
        /*if (file3.exists()) {
            params.addBodyParameter("file3", file3,null);
        }
        if (file4.exists()) {
            params.addBodyParameter("file4", file4,null);
        }*/

        if (file5.exists()) {
            params.addBodyParameter("file5", file5);
        }
        if (file6.exists()) {
            params.addBodyParameter("file6", file6);
        }
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
