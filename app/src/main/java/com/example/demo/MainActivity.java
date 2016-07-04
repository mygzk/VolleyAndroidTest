package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.demo.fileupload.FileHelper;
import com.example.demo.volley.MultipartRequest;
import com.example.demo.volley.VolleyHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener {
    private String TAG = MainActivity.class.getSimpleName();

    //  private String url = "http://172.16.0.10:8080/1.txt";
    private String url = "http://192.168.253.13:80/test.php?id=12&s=2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Volley.newRequestQueue(this.getApplicationContext());

        findViewById(R.id.btn_get).setOnClickListener(this);
        findViewById(R.id.btn_post).setOnClickListener(this);
        findViewById(R.id.btn_post2).setOnClickListener(this);
        findViewById(R.id.btn_post3).setOnClickListener(this);
        findViewById(R.id.btn_post4).setOnClickListener(this);

    }

    private void getTest(String url) {

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "response:" + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "error:" + error.toString());
                error.printStackTrace();
            }
        });

        VolleyHelper.startRequest(stringRequest);
    }

    String rootPath = Environment.getExternalStorageDirectory().getPath();
    String f0 = rootPath + File.separator + "police/1.jpg";
    String f2 = rootPath + File.separator + "4.jpg";
    String f3 = rootPath + File.separator + "5.jpg";

    private void postTest() {
        File file0 = new File(f0);
        File file2 = new File(f2);
        File file3 = new File(f3);
        if (file0.exists()) {
            Log.e("file", "file0 is exists");
        }
        if (file2.exists()) {
            Log.e("file", "file2 is exists");
        }
        if (file3.exists()) {
            Log.e("file", "file3 is exists");
        }
        Map<String, String> parms = new HashMap<String, String>();
        parms.put("s", "ss");
        parms.put("s1", "ss1");
        MultipartRequest multipartRequest = new MultipartRequest(url,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                },
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                "file",
                file0,
                parms);

    }

    private void postTest1() {
        File file0 = new File(f0);
        File file2 = new File(f2);
        File file3 = new File(f3);
        if (file0.exists()) {
            Log.e("file", "file0 is exists");
        }
        if (file2.exists()) {
            Log.e("file", "file2 is exists");
        }
        if (file3.exists()) {
            Log.e("file", "file3 is exists");
        }
        Map<String, String> parms = new HashMap<String, String>();
        parms.put("s", "ss");
        parms.put("s1", "ss1");

        List<File> list = new ArrayList<File>();
        list.add(new File(f0));
        list.add(new File(f2));
        list.add(new File(f3));
        MultipartRequest multipartRequest = new MultipartRequest(url,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                },
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                "file1",
                list,
                parms);

    }

    private void postTest3() {
        File file0 = new File(f0);
        File file2 = new File(f2);
        File file3 = new File(f3);
        if (file0.exists()) {
            Log.e("file", "file0 is exists");
        }
        if (file2.exists()) {
            Log.e("file", "file2 is exists");
        }
        if (file3.exists()) {
            Log.e("file", "file3 is exists");
        }
        Map<String, String> parms = new HashMap<String, String>();
        parms.put("s", "ss");
        parms.put("s1", "ss1");

        FileHelper.startUpload(file0,url);

    }

    private void postTest4() {
        File file0 = new File(f0);
        File file2 = new File(f2);
        File file3 = new File(f3);
        if (file0.exists()) {
            Log.e("file", "file0 is exists");
        }
        if (file2.exists()) {
            Log.e("file", "file2 is exists");
        }
        if (file3.exists()) {
            Log.e("file", "file3 is exists");
        }

        List<File> files = new ArrayList<File>();
        files.add(file0);
        files.add(file2);
        files.add(file3);

        FileHelper.startUpload(files,url);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                getTest(url);
                break;
            case R.id.btn_post:
                postTest();
                break;
            case R.id.btn_post2:
                postTest1();
                break;
            case R.id.btn_post3:
                postTest3();
                break;
            case R.id.btn_post4:
                postTest4();
                break;
        }
    }
}
