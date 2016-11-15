package com.example.duacomputer.helloapp;

/**
 * Created by dua computer on 11/15/2016.
 */
public class MyClass {
    String name, email;
    int age;

    public MyClass() {

    }

    public MyClass(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}
