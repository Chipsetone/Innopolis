package com.semakin.lection5.serializer.Objects;

public class Student {
    private String name;
    private int age;
    private String somUseFullInformationMayBeHereAndItsNotUsefullForYou;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.somUseFullInformationMayBeHereAndItsNotUsefullForYou = "veryImportant!";
    }
}
