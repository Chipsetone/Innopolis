package com.semakin.labs.lab2.dbrestore;

import com.semakin.labs.lab2.dao.AbstractDAO;
import com.semakin.labs.lab2.dbMarshallers.AbstractDbMarshaller;
import com.semakin.labs.lab2.entities.User;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.concurrent.Callable;

/**
 * @author Семакин Виктор
 */
public class UserTableRestorer extends AbstractTableRestorer<User> implements Callable<Boolean>{
    private static final Logger logger = Logger.getLogger(UserTableRestorer.class);

    private AbstractDAO<User> userDao;

    public UserTableRestorer(String fileName, AbstractDbMarshaller dbMarshaller, AbstractDAO<User> userDao) {
        super(fileName, dbMarshaller);
        this.userDao = userDao;
    }

    @Override
    protected boolean insertEntityInDb(User user) {
        try {
            userDao.insert(user);
            logger.info("Вставлена запись в БД с id = " + user.getId());
            return true;
        } catch (SQLException e) {
            logger.error("Не удалось добавить объект с id= " + user.getId(), e);
        }
        return false;
    }

    @Override
    protected void markAsInserted(User entity) {
        super.markAsInserted(entity.getId());
    }
}
