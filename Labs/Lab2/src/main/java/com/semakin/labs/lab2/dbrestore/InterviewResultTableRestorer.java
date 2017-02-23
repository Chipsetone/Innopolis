package com.semakin.labs.lab2.dbrestore;

import com.semakin.labs.lab2.dao.AbstractDAO;
import com.semakin.labs.lab2.dbMarshallers.AbstractDbMarshaller;
import com.semakin.labs.lab2.entities.InterviewResult;
import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * @author Семакин Виктор
 */
public class InterviewResultTableRestorer extends AbstractTableRestorer<InterviewResult> {
    private static final Logger logger = Logger.getLogger(InterviewResultTableRestorer.class);

    private AbstractDAO<InterviewResult> interviewResultDao;
    private InsertedObjects insertedUsers;
    private InsertedObjects insertedSuperUsers;

    public InterviewResultTableRestorer(String fileName, AbstractDbMarshaller tableMarshaller, AbstractDAO interviewResultDao,
                                        InsertedObjects insertedUsers, InsertedObjects insertedSuperUsers) {
        super(fileName, tableMarshaller);
        this.interviewResultDao = interviewResultDao;
        this.insertedUsers = insertedUsers;
        this.insertedSuperUsers = insertedSuperUsers;
    }

    @Override
    protected boolean insertEntityInDb(InterviewResult entity) {
        if(canInsertEntity(entity)) {
            try {
                interviewResultDao.insert(entity);
                logger.info(entity.getId() + " добавлен");
                return true;
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        else{
            logger.info("ждет вставки дочерних объектов");
        }
        return false;
    }

    private boolean canInsertEntity(InterviewResult interviewResult){
        return true;
//        Long userId = interviewResult.getUser().getId();
//        Long superuserId = interviewResult.getSuperuser().getId();
//
//        boolean isInsertItSelf = isYetInserted(interviewResult.getId());
//        boolean result = insertedUsers.isInserted(userId) && insertedSuperUsers.isInserted(superuserId);
//
//        result = result & !isInsertItSelf;
//        return result;
    }

    @Override
    protected void markAsInserted(InterviewResult entity) {
        super.markAsInserted(entity.getId());
    }
}
