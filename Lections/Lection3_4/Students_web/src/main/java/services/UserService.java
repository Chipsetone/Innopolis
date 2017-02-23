package services;

import models.dao.UserDao;
import models.pojo.User;

/**
 * @author Семакин Виктор
 */
public class UserService {

    public static boolean authorize(String login, String password){
        UserDao userDao = new UserDao();

        User user = userDao.getUserByLoginAndPassword(login, password);

        return user != null;
    }

}
