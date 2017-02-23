package com.semakin.labs.lab2.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Собеседование
 * @author Семакин Виктор
 */
@XmlRootElement
public class Interview {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    @XmlElement
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
}
