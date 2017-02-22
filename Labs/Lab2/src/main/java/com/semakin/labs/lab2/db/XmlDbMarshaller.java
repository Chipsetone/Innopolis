package com.semakin.labs.lab2.db;

import com.semakin.labs.lab2.XmlSerializer;
import com.semakin.labs.lab2.dao.InterviewDAO;
import com.semakin.labs.lab2.dao.InterviewResultDAO;
import com.semakin.labs.lab2.dao.SuperuserDAO;
import com.semakin.labs.lab2.dao.UserDAO;
import com.semakin.labs.lab2.dbMarshallers.InterviewDbMarshaller;
import com.semakin.labs.lab2.dbMarshallers.InterviewResultDbMarshaller;
import com.semakin.labs.lab2.dbMarshallers.SuperuserDbMarshaller;
import com.semakin.labs.lab2.dbMarshallers.UserDbMarshaller;

/**
 * @author Семакин Виктор
 */
public class XmlDbMarshaller {
    public void serializeDbTablesToXml(){
        IConnectionFactory connectionFactory = ConnectionFactory.getInstance();
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
