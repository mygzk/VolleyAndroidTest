package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {
    private String TAG = MainActivity.class.getSimpleName();

    //  private String url = "http://172.16.0.10:8080/1.txt";
    private String url = "http://192.168.253.13:80/test.php?id=12&s=2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_get).setOnClickListener(this);
        findViewById(R.id.btn_post).setOnClickListener(this);
        findViewById(R.id.btn_post2).setOnClickListener(this);
        findViewById(R.id.btn_post3).setOnClickListener(this);
        findViewById(R.id.btn_post4).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
