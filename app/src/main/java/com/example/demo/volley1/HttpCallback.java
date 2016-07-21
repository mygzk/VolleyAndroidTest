package com.example.demo.volley1;

/**
 * Created by guozhk on 16-7-21.
 */
public interface HttpCallback<T> {
     void onStart();
     void onFinish();
     void onResult(T result);
     void onError(Exception e);
     void onCancelled();
     void onLoading(long count, long current);
}
