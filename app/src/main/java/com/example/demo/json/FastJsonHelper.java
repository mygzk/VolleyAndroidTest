package com.example.demo.json;

import com.alibaba.fastjson.JSON;

/**
 * Created by guozhk on 16-7-6.
 */
public class FastJsonHelper {


    public static String toJsonString(Object obj) {
        return JSON.toJSONString(obj);
    }



}
