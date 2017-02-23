package com.semakin.labs.lab2.entities;

import javax.xml.bind.annotation.*;
import java.sql.Date;

/**
 * Результат собеседования
 * @author Семакин Виктор
 */
@XmlRootElement
//@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class InterviewResult {
    private long id;
    private Long userId;
    private Long superUserId;
    private Date createdAt;
    private short totalRating;

    private User user;
    private Superuser superuser;

    public long getId() {
        return id;
    }
    @XmlElement
    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @XmlTransient
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAtString() {
        return getCreatedAt().toString();
    }

    @XmlTransient
    public void setCreatedAtString(String createdAtString) {
        setCreatedAt(Date.valueOf(createdAtString));
    }

    public short getTotalRating() {
        return totalRating;
    }

    @XmlElement
    public void setTotalRating(short totalRating) {
        this.totalRating = totalRating;
    }

    public User getUser() {
        return user;
    }

    @XmlElement
    public void setUser(User user) {
        this.user = user;
    }

    @XmlElement
    public Superuser getSuperuser() {
        return superuser;
    }

    public void setSuperuser(Superuser superuser) {
        this.superuser = superuser;
    }

    public Long getUserId() {
        return userId;
    }

    @XmlTransient()
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getSuperUserId() {
        return superUserId;
    }

    @XmlTransient
    public void setSuperUserId(Long superUserId) {
        this.superUserId = superUserId;
    }
}
