package com.semakin.labs.lab2.dbrestore;

import com.semakin.labs.lab2.dao.AbstractDAO;
import com.semakin.labs.lab2.dbMarshallers.AbstractDbMarshaller;
import com.semakin.labs.lab2.entities.Superuser;
import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * @author Семакин Виктор
 */
public class SuperuserTableRestorer extends AbstractTableRestorer<Superuser> {
    private static final Logger logger = Logger.getLogger(SuperuserTableRestorer.class);

    private AbstractDAO superuserDao;

    public SuperuserTableRestorer(String fileName, AbstractDbMarshaller tableMarshaller, AbstractDAO superuserDao) {
        super(fileName, tableMarshaller);
        this.superuserDao = superuserDao;
    }

    @Override
    protected boolean insertEntityInDb(Superuser entity) {
        try {
            superuserDao.insert(entity);
            logger.info("Вставлена запись в БД с id = " + entity.getId());
            return true;
        } catch (SQLException e) {
            logger.error("Не удалось добавить объект с id= " + entity.getId(),e);
        }

        return false;
    }

    @Override
    protected void markAsInserted(Superuser entity) {
        markAsInserted(entity.getId());
    }
}
