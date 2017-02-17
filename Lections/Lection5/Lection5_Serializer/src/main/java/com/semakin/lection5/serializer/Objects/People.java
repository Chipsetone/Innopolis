package com.semakin.lection5.serializer.Objects;

public class People {
    private String name;
    private double salary;
    private int age;

    public String publicField;
    public static int staticField;

    public People(String name, double salary, int age, String publicField) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.publicField = publicField;
    }
}
