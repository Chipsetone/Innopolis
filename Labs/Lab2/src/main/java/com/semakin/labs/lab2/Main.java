package com.semakin.labs.lab2;

import com.semakin.labs.lab2.dao.UserDAO;
import com.semakin.labs.lab2.db.ConnectionFactory;
import com.semakin.labs.lab2.entities.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public class Main {
    public static void main(String[] args) throws SQLException, NoSuchFieldException, IllegalAccessException {
        ConnectionFactory factory = ConnectionFactory.getInstance();

        UserDAO userDao = new UserDAO(factory);
        // INSERT
        User newUser = new User(){{
            setLastName("Фамилия");
            setFirstName("Имя");
            setMiddleName("Отчество");
            setBirthDate(new Date(2017, 02, 02));
            setBitrix_id(42);
            setPhone("999123123123");
            setEmail("mail@mail.mail");

        }};
        userDao.insert(newUser);
        List<User> users = userDao.selectAll();

        for (User user :
                users) {
            System.out.println(user.getId());
        }
    }
}
