package com.stone.myapplication;

import com.stone.myapplication.annotation.Say;

/**
 * author : stone
 * time   : 15/4/9 15 06
 * email  : aa86799@163.com
 */
@Say(sayHello = "a", sayHi = "b")
public class User {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
