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
    private String createdAt;
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

    public Date getCreatedAtDate() {
        return Date.valueOf(getCreatedAt());
    }

    @XmlTransient
    public void setCreatedAtDate(Date createdAtDate) {
        this.setCreatedAt(createdAtDate.toString());
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @XmlElement(name = "createdAt")
    public void setCreatedAt(String createdAtString) {
//        setCreatedAt(Date.valueOf(createdAtString));
        this.createdAt = createdAtString;
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
        this.setUserId(user.getId());
    }

    @XmlElement
    public Superuser getSuperuser() {
        return superuser;
    }

    public void setSuperuser(Superuser superuser) {
        this.superuser = superuser;
        this.setSuperUserId(superuser.getId());
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
