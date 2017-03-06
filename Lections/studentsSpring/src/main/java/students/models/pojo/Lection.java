package students.models.pojo;

import java.sql.Date;

public class Lection {

    private int id;
    private String name;
    private String subject;
    private String textLection;
    private int groupid;
    private Date date;

    public Lection(int id, String name, String subject, String textLection, int groupid, Date date) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.textLection = textLection;
        this.groupid = groupid;
        this.date = date;
    }

    public Lection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTextLection() {
        return textLection;
    }

    public void setTextLection(String textLection) {
        this.textLection = textLection;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
