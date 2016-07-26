package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_volley).setOnClickListener(this);
        findViewById(R.id.btn_okhttp).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_volley:
                intent = new Intent(MainActivity.this, MainVolleyActivity.class);
                break;
            case R.id.btn_okhttp:
                intent = new Intent(MainActivity.this, MainOkhttpActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
