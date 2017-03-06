package students.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import students.common.exceptions.UserDAOException;
import students.models.dao.UserDAO;
import students.models.pojo.User;

import java.util.Random;

@Service
public class UserService implements IUserService{
    private static Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private UserDAO userDAO;

    private int anInt;


    @Override
    public User authorise(String login, String password) throws UserDAOException {
        if(anInt == 0) {
            Random rand = new Random(100);
            anInt = rand.nextInt();
        }
        logger.trace("userService anInt = " + anInt);

        return userDAO.getUserByLoginAndPassword(login, password);
    }

    public boolean registration(String login, String password, String email) throws UserDAOException {
        return userDAO.registrationUser(login, password,email);
    }
}
