package com.semakin.labs.lab2.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

/**
 * Пользователь из битрикса - абитуриент, студент
 * @author Семакин Виктор
 */
@XmlRootElement
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

    @XmlElement
    public void setId(long id) {
        this.id = id;
    }

    public long getBitrix_id() {
        return bitrix_id;
    }

    @XmlElement
    public void setBitrix_id(long bitrix_id) {
        this.bitrix_id = bitrix_id;
    }

    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    @XmlElement
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    @XmlElement
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    @XmlElement
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
