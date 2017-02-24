package com.semakin.labs.lab2.dbrestore;

import com.semakin.labs.lab2.XmlListEntities.IListEntities;
import com.semakin.labs.lab2.dbMarshallers.AbstractDbMarshaller;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Семакин Виктор
 */
public abstract class AbstractTableRestorer<T> implements Callable<Boolean> {
    private static final Logger logger = Logger.getLogger(AbstractTableRestorer.class);

    private final String fileName;
    private AbstractDbMarshaller<T> tableMarshaller;
    private InsertedObjects insertedObjects;

    public AbstractTableRestorer(final String fileName, AbstractDbMarshaller tableMarshaller) {
        this.fileName = fileName;
        this.tableMarshaller = tableMarshaller;
        this.insertedObjects = new InsertedObjects();
    }

    @Override
    public Boolean call() throws Exception {
        IListEntities<T> entitiesList = tableMarshaller.unmarshallTable(fileName);

        List<T> entitiesForDb = entitiesList.getList();
        insertAllEntitiesInDb(entitiesForDb);

        return true;
    }


    public InsertedObjects getInsertedObjects() {
        return insertedObjects;
    }

    protected void insertAllEntitiesInDb(List<T> entities) {
        for (T entity :
                entities) {

            if(insertEntityInDb(entity)){
                markAsInserted(entity);
            }
        }
        getInsertedObjects().setStopped(true);
        logger.info(fileName + " заполнение таблицы БД завершено");
    }

    protected abstract boolean insertEntityInDb(T entity);

    protected abstract void markAsInserted(T entity);

    protected void markAsInserted(long id){
        insertedObjects.markAsInserted(id);
    }

    protected boolean isYetInserted(Long id){
        return insertedObjects.isInserted(id);
    }

    public String getFileName() {
        return fileName;
    }
}
