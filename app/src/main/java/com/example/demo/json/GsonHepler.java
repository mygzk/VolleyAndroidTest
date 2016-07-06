package com.example.demo.json;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by guozhk on 16-7-6.
 */
public class GsonHepler {

    private static Gson gson = new Gson();

    public static <T> T object(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T object(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static <T> String gsonToJson(Class<T> param) {
        return gson.toJson(param);
    }

    public static <T> String gsonToJson(List<T> param, Type type) {
        return gson.toJson(param, type);
    }
}
