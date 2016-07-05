package com.example.demo.volley.util;

import com.google.gson.Gson;

public class JsonUtils {
	private static Gson gson = new Gson();

	public static <T> T object(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}
	public static <T> String toJson(Class<T> param) {
		return gson.toJson(param);
	}
}
