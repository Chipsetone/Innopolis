package com.semakin.labs.lab2.XmlListEntities;

import com.semakin.labs.lab2.entitiessimple.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Семакин Виктор
 */
@XmlRootElement(name = "users")
public class UserList{
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    @XmlElement(name = "user")
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
