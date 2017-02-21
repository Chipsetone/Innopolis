package com.semakin.labs.lab2.entities;

import java.sql.Date;

/**
 * @author Семакин Виктор
 */
public class User {
    private long id;
    private long bitrix_id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private Date birthDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBitrix_id() {
        return bitrix_id;
    }

    public void setBitrix_id(long bitrix_id) {
        this.bitrix_id = bitrix_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
