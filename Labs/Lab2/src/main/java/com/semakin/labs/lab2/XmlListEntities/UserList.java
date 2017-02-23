package com.semakin.labs.lab2.XmlListEntities;

import com.semakin.labs.lab2.entities.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Семакин Виктор
 */
@XmlRootElement(name = "users")
public class UserList implements  IListEntities<User>{
    private List<User> userList;

    public List<User> getList() {
        return userList;
    }

    @XmlElement(name = "user")
    public void setList(List<User> userList) {
        this.userList = userList;
    }
}
