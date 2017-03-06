package students.services;

import students.common.exceptions.UserDAOException;
import students.models.pojo.User;

/**
 * @author Семакин Виктор
 */
public interface IUserService {
    User authorise(String login, String password) throws UserDAOException;
}
