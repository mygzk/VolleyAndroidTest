package com.example.demo.volley;

import android.content.Context;

public class IRequest {
	/**
	 * 返回String get
	 * 
	 * @param context
	 * @param url
	 * @param l
	 */
	public static void get(Context context, String url, RequestListener l) {
		RequestManager.get(url, context, l);
	}


	/**
	 * 返回对象 get
	 * 
	 * @param context
	 * @param url
	 * @param classOfT
	 * @param l
	 * @param <T>
	 */
	public static <T> void get(Context context, String url, Class<T> classOfT,
			RequestJsonListener<T> l) {
		RequestManager.get(url, context, classOfT, l);
	}


	/**
	 * 返回String post
	 * 
	 * @param context
	 * @param url
	 * @param params
	 * @param l
	 */
	public static void post(Context context, String url, RequestParams params,
			RequestListener l) {
		RequestManager.post(url, context, params, l);
	}

	/**
	 * 返回对象 post
	 * 
	 * @param context
	 * @param url
	 * @param classOfT
	 * @param params
	 * @param l
	 */
	public static <T> void post(Context context, String url, Class<T> classOfT,
			RequestParams params, RequestJsonListener<T> l) {
		RequestManager.post(url, context, classOfT, params, l);
	}


}
