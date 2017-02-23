package com.semakin.labs.lab2.dbrestore;

import com.semakin.labs.lab2.dao.AbstractDAO;
import com.semakin.labs.lab2.dbMarshallers.AbstractDbMarshaller;
import com.semakin.labs.lab2.entities.Interview;
import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * @author Семакин Виктор
 */
public class InterviewTableRestorer extends AbstractTableRestorer<Interview> {
    private static final Logger logger = Logger.getLogger(InterviewResultTableRestorer.class);
    private AbstractDAO interviewDao;

    public InterviewTableRestorer(String fileName, AbstractDbMarshaller tableMarshaller, AbstractDAO interviewDao) {
        super(fileName, tableMarshaller);
        this.interviewDao = interviewDao;
    }

    @Override
    protected boolean insertEntityInDb(Interview entity) {
        try {
            interviewDao.insert(entity);
            logger.info("Вставлена запись в БД с id = " + entity.getId());
            return true;
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    protected void markAsInserted(Interview entity) {
        super.markAsInserted(entity.getId());
    }
}
