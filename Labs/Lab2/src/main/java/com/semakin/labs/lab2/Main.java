package com.semakin.labs.lab2;

import com.semakin.labs.lab2.connection.ConnectionFactory;
import com.semakin.labs.lab2.connection.IConnectionFactory;
import com.semakin.labs.lab2.dao.*;
import com.semakin.labs.lab2.dbMarshallers.*;
import com.semakin.labs.lab2.dbrestore.*;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author Семакин Виктор
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args)  {
        //ExecutorService service = Executors.newFixedThreadPool(4);

        serializeTables();
        cleanTables();
        deserializeTables();
    }

    private static void serializeTables(){
        AllTablesMarshaller allTablesMarshaller = new AllTablesMarshaller(ConnectionFactory.getInstance());
        allTablesMarshaller.marshallTables();
    }

    private static void cleanTables(){
        XmlSerializer serializer = new XmlSerializer();
        IConnectionFactory connectionFactory = ConnectionFactory.getInstance();

        AbstractDAO[] allTablesDao = {
                new InterviewResultDAO(connectionFactory),
                new UserDAO(connectionFactory),
                new SuperuserDAO(connectionFactory),
                new InterviewDAO(connectionFactory)
        };

        for (AbstractDAO dao :
                allTablesDao) {
            dao.deleteAll();
        }
    }

    private static void deserializeTables(){
        XmlSerializer serializer = new XmlSerializer();
        IConnectionFactory connectionFactory = ConnectionFactory.getInstance();

        AbstractDbMarshaller userDbMarshaller = new UserDbMarshaller(serializer);
        AbstractDAO userDao = new UserDAO(connectionFactory);
        UserTableRestorer userTableRestorer = new UserTableRestorer(FileNames.userFilename, userDbMarshaller,  userDao);
        try {
            userTableRestorer.call();
        } catch (Exception e) {
            logger.error(e);
        }

        AbstractDbMarshaller superuserDbMarshaller = new SuperuserDbMarshaller(serializer);
        AbstractDAO superuserDao = new SuperuserDAO(connectionFactory);
        SuperuserTableRestorer superuserTableRestorer = new SuperuserTableRestorer(FileNames.superUserFileName,
                superuserDbMarshaller, superuserDao);

        try {
            superuserTableRestorer.call();
        } catch (Exception e) {
            logger.error(e);
        }

        AbstractDbMarshaller interviewMarshaller = new InterviewDbMarshaller(serializer);
        AbstractDAO interviewDao = new InterviewDAO(connectionFactory);
        InterviewTableRestorer interviewTableRestorer = new InterviewTableRestorer(FileNames.interviewFileName,
                interviewMarshaller, interviewDao);

        try {
            interviewTableRestorer.call();
        } catch (Exception e) {
            logger.error(e);
        }

        InsertedObjects insertedUsers = new InsertedObjects();
        InsertedObjects insertedSuperUsers = new InsertedObjects();

        AbstractDbMarshaller interviewResultMarshaller = new InterviewResultDbMarshaller(serializer);
        AbstractDAO interviewresultDao = new InterviewResultDAO(connectionFactory);
        InterviewResultTableRestorer interviewResultTableRestorer = new InterviewResultTableRestorer(FileNames.interviewResultFileName,
                interviewResultMarshaller, interviewresultDao, insertedUsers, insertedSuperUsers);

        try {
            interviewResultTableRestorer.call();
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
