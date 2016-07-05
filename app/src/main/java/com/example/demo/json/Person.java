package com.example.demo.json;

import java.util.List;

/**
 * Created by guozhk on 16-7-5.
 */
public class Person {

    private String name;
    private int age;
    private List<Person> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
