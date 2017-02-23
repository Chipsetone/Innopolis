package com.semakin.labs.lab2.dbMarshallers;

import com.semakin.labs.lab2.XmlSerializer;
import com.semakin.labs.lab2.dao.InterviewDAO;
import com.semakin.labs.lab2.dao.InterviewResultDAO;
import com.semakin.labs.lab2.dao.SuperuserDAO;
import com.semakin.labs.lab2.dao.UserDAO;
import com.semakin.labs.lab2.db.IConnectionFactory;

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
        interviewMarshaller.marshalTable(interviewDAO, "interviews.xml");

        UserDbMarshaller userMarshaller = new UserDbMarshaller(serializer);
        UserDAO userDAO = new UserDAO(connectionFactory);
        userMarshaller.marshalTable(userDAO, "users.xml");

        SuperuserDbMarshaller superuserDbMarshaller = new SuperuserDbMarshaller(serializer);
        SuperuserDAO superuserDAO = new SuperuserDAO(connectionFactory);
        superuserDbMarshaller.marshalTable(superuserDAO, "superusers.xml");

        InterviewResultDbMarshaller interviewResultMarshaller = new InterviewResultDbMarshaller(serializer);
        InterviewResultDAO interviewResultDAO = new InterviewResultDAO(connectionFactory);
        interviewResultMarshaller.marshalTable(interviewResultDAO, "interviewresults.xml");
    }
}
