package com.semakin.labs.lab2.dbMarshallers;

import com.semakin.labs.lab2.XmlSerializer;
import com.semakin.labs.lab2.dao.InterviewDAO;
import com.semakin.labs.lab2.dao.InterviewResultDAO;
import com.semakin.labs.lab2.dao.SuperuserDAO;
import com.semakin.labs.lab2.dao.UserDAO;
import com.semakin.labs.lab2.connection.IConnectionFactory;

/**
 * @author Семакин Виктор
 */
public class AllTablesMarshaller {
    private IConnectionFactory connectionFactory;

    public AllTablesMarshaller(IConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void marshallTables(){
        XmlSerializer serializer = new XmlSerializer();

        InterviewDbMarshaller interviewMarshaller = new InterviewDbMarshaller(serializer);
        InterviewDAO interviewDAO = new InterviewDAO(connectionFactory);
        interviewMarshaller.marshalTable(interviewDAO, FileNames.interviewFileName);

        UserDbMarshaller userMarshaller = new UserDbMarshaller(serializer);
        UserDAO userDAO = new UserDAO(connectionFactory);
        userMarshaller.marshalTable(userDAO, FileNames.userFilename);

        SuperuserDbMarshaller superuserDbMarshaller = new SuperuserDbMarshaller(serializer);
        SuperuserDAO superuserDAO = new SuperuserDAO(connectionFactory);
        superuserDbMarshaller.marshalTable(superuserDAO, FileNames.superUserFileName);

        InterviewResultDbMarshaller interviewResultMarshaller = new InterviewResultDbMarshaller(serializer);
        InterviewResultDAO interviewResultDAO = new InterviewResultDAO(connectionFactory);
        interviewResultMarshaller.marshalTable(interviewResultDAO, FileNames.interviewResultFileName);
    }

    public void unmarshallTables(){
        XmlSerializer serializer = new XmlSerializer();

        AbstractDbMarshaller interviewDbMarshaller = new InterviewDbMarshaller(serializer);
        AbstractDbMarshaller userDbMarshaller = new UserDbMarshaller(serializer);
        AbstractDbMarshaller superuserDbMarshaller = new SuperuserDbMarshaller(serializer);
        AbstractDbMarshaller interviewResultDbMarshaller = new InterviewResultDbMarshaller(serializer);

        interviewDbMarshaller.unmarshallTable(FileNames.interviewFileName);
        superuserDbMarshaller.unmarshallTable(FileNames.superUserFileName);
        userDbMarshaller.unmarshallTable(FileNames.userFilename);
        interviewResultDbMarshaller.unmarshallTable(FileNames.interviewResultFileName);
    }
}
