package com.semakin.labs.lab2;

import com.semakin.labs.lab2.connection.ConnectionFactory;
import com.semakin.labs.lab2.connection.IConnectionFactory;
import com.semakin.labs.lab2.dao.*;
import com.semakin.labs.lab2.dbMarshallers.*;
import com.semakin.labs.lab2.dbrestore.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Семакин Виктор
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args)  {

        serializeTables();
        cleanTables();
        deserializeTables();
    }

    private static void serializeTables(){
        XmlSerializer serializer = new XmlSerializer();
        IConnectionFactory connectionFactory = ConnectionFactory.getInstance();

        InterviewDbMarshaller interviewMarshaller = new InterviewDbMarshaller(serializer);
        InterviewDAO interviewDAO = new InterviewDAO(connectionFactory);

        UserDbMarshaller userMarshaller = new UserDbMarshaller(serializer);
        UserDAO userDAO = new UserDAO(connectionFactory);

        SuperuserDbMarshaller superuserDbMarshaller = new SuperuserDbMarshaller(serializer);
        SuperuserDAO superuserDAO = new SuperuserDAO(connectionFactory);

        InterviewResultDbMarshaller interviewResultMarshaller = new InterviewResultDbMarshaller(serializer);
        InterviewResultDAO interviewResultDAO = new InterviewResultDAO(connectionFactory);

        interviewMarshaller.marshalTable(interviewDAO, FileNames.interviewFileName);
        userMarshaller.marshalTable(userDAO, FileNames.userFilename);
        superuserDbMarshaller.marshalTable(superuserDAO, FileNames.superUserFileName);
        interviewResultMarshaller.marshalTable(interviewResultDAO, FileNames.interviewResultFileName);
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

        logger.info("таблицы БД очищены");
    }

    private static void deserializeTables(){
        XmlSerializer serializer = new XmlSerializer();
        IConnectionFactory connectionFactory = ConnectionFactory.getInstance();

        AbstractDbMarshaller userDbMarshaller = new UserDbMarshaller(serializer);
        AbstractDAO userDao = new UserDAO(connectionFactory);
        UserTableRestorer userTableRestorer = new UserTableRestorer(FileNames.userFilename, userDbMarshaller,  userDao);


        AbstractDbMarshaller superuserDbMarshaller = new SuperuserDbMarshaller(serializer);
        AbstractDAO superuserDao = new SuperuserDAO(connectionFactory);
        SuperuserTableRestorer superuserTableRestorer = new SuperuserTableRestorer(FileNames.superUserFileName,
                superuserDbMarshaller, superuserDao);


        AbstractDbMarshaller interviewMarshaller = new InterviewDbMarshaller(serializer);
        AbstractDAO interviewDao = new InterviewDAO(connectionFactory);
        InterviewTableRestorer interviewTableRestorer = new InterviewTableRestorer(FileNames.interviewFileName,
                interviewMarshaller, interviewDao);

        InsertedObjects insertedUsers = userTableRestorer.getInsertedObjects();
        InsertedObjects insertedSuperUsers = superuserTableRestorer.getInsertedObjects();

        AbstractDbMarshaller interviewResultMarshaller = new InterviewResultDbMarshaller(serializer);
        AbstractDAO interviewresultDao = new InterviewResultDAO(connectionFactory);
        InterviewResultTableRestorer interviewResultTableRestorer = new InterviewResultTableRestorer(FileNames.interviewResultFileName,
                interviewResultMarshaller, interviewresultDao, insertedUsers, insertedSuperUsers);


//        ExecutorService service = Executors.newFixedThreadPool(4);
//        List<Callable<Boolean>> tasks = new ArrayList<>();
//        tasks.add(interviewTableRestorer);
//        tasks.add(userTableRestorer);
//        tasks.add(superuserTableRestorer);
//        tasks.add(interviewResultTableRestorer);

        try {
            interviewTableRestorer.call();
            userTableRestorer.call();
            superuserTableRestorer.call();
            interviewResultTableRestorer.call();
        } catch (Exception e) {
            logger.error(e);
        }

    }
}
