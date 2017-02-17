package com.semakin.jdbc.DTO;

import com.semakin.jdbc.entitylogic.Entity;

import java.sql.Date;


/**
 * @author Семакин Виктор
 */
public class Student extends Entity {
    private String name;
    private Date birthDate;
    private Boolean sex;

    public Student(long id, String name, Date birthDate, Boolean sex) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
}
