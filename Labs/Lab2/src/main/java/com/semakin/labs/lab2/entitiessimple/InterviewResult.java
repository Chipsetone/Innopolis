package com.semakin.labs.lab2.entitiessimple;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

/**
 * Результат собеседования
 * @author Семакин Виктор
 */
@XmlRootElement
public class InterviewResult {
    private long id;
    private long userId;
    private long superUserId;
    private Date createdAt;
    private short totalRating;

    public long getId() {
        return id;
    }
    @XmlElement
    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    @XmlElement
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSuperUserId() {
        return superUserId;
    }

    @XmlElement
    public void setSuperUserId(long superUserId) {
        this.superUserId = superUserId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @XmlElement
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public short getTotalRating() {
        return totalRating;
    }

    @XmlElement
    public void setTotalRating(short totalRating) {
        this.totalRating = totalRating;
    }
}
