package com.example.demo.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by guozhk on 16-7-5.
 */
public class JsonMainTest {

    //static  String jsonStr = "[{\"name\":\"name0\",\"age\":0},{\"name\":\"name1\",\"age\":5},{\"name\":\"name2\",\"age\":10},{\"name\":\"name3\",\"age\":15},{\"name\":\"name4\",\"age\":20},{\"name\":\"name5\",\"age\":25},{\"name\":\"name6\",\"age\":30},{\"name\":\"name7\",\"age\":35},{\"name\":\"name8\",\"age\":40},{\"name\":\"name9\",\"age\":45}]";
    static String jsonStr = "[{\"name\":\"name0\",\"age\":\"0\",\"list\":[{\"name\":\"name00\",\"age\":\"00\"},{\"name\":\"name01\",\"age\":\"01\"}]},{\"name\":\"name0\",\"age\":\"0\",\"list\":[{\"name\":\"name10\",\"age\":\"10\"}]},{\"name\":\"name1\",\"age\":\"1\"}]";
    private static Gson gson = new Gson();

    public static void main(String[] args) {
       /* List<Person> ps = gson.fromJson(jsonStr, new TypeToken<List<Person>>() {
        }.getType());*/
        List<Person> ps = GsonHepler.object(jsonStr, new TypeToken<List<Person>>() {
        }.getType());

        for (int i = 0; i < ps.size(); i++) {
            System.out.print("name-" + i + ":" + ps.get(i).getName() + "  ");
            System.out.println("age-" + i + ":" + ps.get(i).getAge());
        }

        String s = GsonHepler.gsonToJson(ps, new TypeToken<List<Person>>() {
        }.getType());
        System.out.print("s-" + s);

    }


}
