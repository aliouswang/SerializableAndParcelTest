package com.example.aliouswang.serializableandparceltest;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    public int age;
    public String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

}
