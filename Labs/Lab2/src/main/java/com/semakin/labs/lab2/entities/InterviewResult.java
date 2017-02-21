package com.semakin.labs.lab2.entities;/**
 * Created by Chi on 21.02.2017.
 */

import java.sql.Date;

/**
 * @author Семакин Виктор
 */
public class InterviewResult {
    private long id;
    private long userId;
    private long superUserId;
    private Date createdAt;
    private short totalRating;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSuperUserId() {
        return superUserId;
    }

    public void setSuperUserId(long superUserId) {
        this.superUserId = superUserId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public short getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(short totalRating) {
        this.totalRating = totalRating;
    }
}
