package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.demo.volley.IRequest;
import com.example.demo.volley.RequestListener;
import com.example.demo.volley.RequestParams;
import com.example.demo.volley1.HttpCallback;
import com.example.demo.volley1.HttpTools;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.xutils.common.Callback;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainVolleyActivity extends Activity implements View.OnClickListener {
    private String TAG = MainVolleyActivity.class.getSimpleName();
    private String urlGet = "http://172.16.0.32/User.svc/ShowName?name=gzk";

    //private String urlPost = "http://172.16.0.32:7789/MySericeDemo/postMostStr/8";
    //private String urlPost = "http://172.16.0.32:7789/MySericeDemo/postMostStr";
    //private String urlPost = "http://172.16.0.32:82/User.svc/mex";

    private String urlPost = "http://172.16.0.32:85/test";
    //private String urlPost = "http://172.16.0.32:85/test/index";

   // private String urlPost1 = "http://192.168.191.10:80/test.php";
    private String urlPost1 = "http://192.168.253.13:80/test.php";

    private TextView tvResult;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private HttpTools mHttpTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_volley);
        mHttpTools =  HttpTools.initHttpTools (this);


        x.Ext.init(this.getApplication());//Xutils初始化

        findViewById(R.id.btn_volleyget).setOnClickListener(this);
        findViewById(R.id.btn_volleypost).setOnClickListener(this);
        findViewById(R.id.btn_xutilget).setOnClickListener(this);
        findViewById(R.id.btn_xutilpost).setOnClickListener(this);
        findViewById(R.id.btn_result_empty).setOnClickListener(this);
        findViewById(R.id.btn_volleypost1).setOnClickListener(this);
        tvResult = (TextView) findViewById(R.id.result);
        //ATTENTION: This was auto-generated to implement the App Indexing API.
        //See https://g.co/AppIndexing/AndroidStudio for more information.
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

            case R.id.btn_volleypost1:
                urlPost1();
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
    String f7 = rootPath + File.separator + "7.txt";
    String f8 = rootPath + File.separator + "8.xml";
    String f9 = rootPath + File.separator + "1.txt";
    String f10 = rootPath + File.separator + "2.txt";
    String f11 = rootPath + File.separator + "3.txt";
    String f12 = rootPath + File.separator + "4.txt";
    String f13 = rootPath + File.separator + "5.txt";
    File file1 = new File(f1);
    File file2 = new File(f2);
    File file3 = new File(f3);
    File file4 = new File(f4);
    File file5 = new File(f5);
    File file6 = new File(f6);
    File file7 = new File(f7);
    File file8 = new File(f8);
    File file9 = new File(f9);
    File file10 = new File(f10);
    File file11 = new File(f11);
    File file12 = new File(f12);
    File file13 = new File(f13);

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

        params.put("Age", "gzk_post");
        params.put("Name", "zayh");
        params.put("com1", "zayh1");
       /* if (file10.exists()) {
            params.put("File", f9);
        }
        if (file1.exists()) {
            params.put("img1", file1);
        }*/
        if (file1.exists()) {
            params.put("File", file1);
        }
        if (file2.exists()) {
            params.put("img1", file2);
        }
        if (file3.exists()) {
            params.put("file3", file3);
        }
        if (file4.exists()) {
            params.put("file4", file4);
        }
        if (file5.exists()) {
            params.put("file5", file5);
        }
        if (file6.exists()) {
            params.put("file6", file6);
        }
        if (file7.exists()) {
            params.put("file7", file7);
        }
        if (file8.exists()) {
            params.put("file8", file8);
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
        params.addBodyParameter("Age", "gzk");
        params.addBodyParameter("Name", "lxx");
        if (file10.exists()) {
            params.addBodyParameter("File", file10, null);
        }
        if (file1.exists()) {
            params.addBodyParameter("img1", file1);
        }

        List<File> fileList = new ArrayList<File>();
        if (file2.exists()) {

            fileList.add(file2);
        }
        if (file9.exists()) {

            fileList.add(file9);
        }
        params.addParameter("filelist", fileList);

        /*if (file1.exists()) {
            params.addBod
            yParameter("file1", file1,null);
        }*/
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
/*
        if (file5.exists()) {
            params.addBodyParameter("file5", file5);
        }
        if (file6.exists()) {
            params.addBodyParameter("file6", file6);
        }
        if (file7.exists()) {
            params.addBodyParameter("file7", file7);
        }
        if (file8.exists()) {
            params.addBodyParameter("file8", file8);
        }*/
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

    private void urlPost1(){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("Age", "gzk_post");
        params.put("Name", "zayh");
        params.put("com1", "zayh1");
        if (file1.exists()) {
            params.put("File", file1);
        }
        if (file2.exists()) {
            params.put("img1", file2);
        }
        if (file5.exists()) {
            params.put("file5", file5);
        }
        if (file6.exists()) {
            params.put("file6", file6);
        }
        if (file7.exists()) {
            params.put("file7", file7);
        }
        if (file8.exists()) {
            params.put("file8", file8);
        }
        mHttpTools.post(urlPost,params , new HttpCallback<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onResult(String result) {
                Log.e(TAG,"onResult:"+result);
            }


            @Override
            public void onError(Exception e) {
                Log.e(TAG,"onError:"+e.toString());
                e.printStackTrace();
            }

            @Override
            public void onCancelled() {
                Log.e(TAG,"onCancelled:");
            }

            @Override
            public void onLoading(long count, long current) {
                Log.e(TAG,"onLoading  count:"+count + " current:"+current);
            }
        });
    }

}
