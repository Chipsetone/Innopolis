package com.semakin.labs.lab2;

import com.semakin.labs.lab2.dao.InterviewDAO;
import com.semakin.labs.lab2.dao.InterviewResultDAO;
import com.semakin.labs.lab2.dao.SuperuserDAO;
import com.semakin.labs.lab2.dao.UserDAO;
import com.semakin.labs.lab2.db.ConnectionFactory;
import com.semakin.labs.lab2.db.IConnectionFactory;
import com.semakin.labs.lab2.dbMarshallers.*;
import com.semakin.labs.lab2.entitiessimple.Interview;
import com.semakin.labs.lab2.entitiessimple.InterviewResult;
import com.semakin.labs.lab2.entitiessimple.Superuser;
import com.semakin.labs.lab2.entitiessimple.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * @author Семакин Виктор
 */
public class Main {
    public static void main(String[] args) throws SQLException, NoSuchFieldException, IllegalAccessException {
        PresentSerializeList();
    }

    private static void PresentSerializeList(){
        AllTablesMarshaller allTablesMarshaller = new AllTablesMarshaller(ConnectionFactory.getInstance());
        allTablesMarshaller.marshallTables();
    }

    private static void presentXML(){
        Random rand = new Random();
        final int randNumber = rand.nextInt();

        Interview interview = new Interview() {{
            setName("" + randNumber);
        }};

        XmlSerializer serializer = new XmlSerializer();

        serializer.serializeToFile(Interview.class, interview, "file.xml");
        Interview deserializedInterview = (Interview) serializer.deserializeFromFile(Interview.class, "file.xml");

        printInterview(deserializedInterview);

    }

    private static void presentDbWork() throws SQLException {
        IConnectionFactory factory = ConnectionFactory.getInstance();

        System.out.println("user");
        presentUser(factory);

        System.out.println("\n\n\n---------\nsuperuser");
        presentSuperUser(factory);

        System.out.println("\n\n\n---------\ninterview");
        presentInterview(factory);

        System.out.println("\n\n\n---------\ninterview_result");
        presentInterviewResult(factory);
    }

    private static void presentInterviewResult(IConnectionFactory factory) throws SQLException {
        InterviewResultDAO interviewResultDAO = new InterviewResultDAO(factory);
        Random rand = new Random();

        final int randNumber = rand.nextInt();

        // INSERT
        InterviewResult interviewResult = new InterviewResult() {{
            setCreatedAt(new Date(randNumber));
            setTotalRating((short)randNumber);
            setUserId(new Long(10));
            setSuperUserId(new Long(27));
        }};

        interviewResultDAO.insert(interviewResult);

        //SELECT ALL
        List<InterviewResult> interviewResults = interviewResultDAO.selectAll();
        for (InterviewResult item :
                interviewResults) {
            printInterviewResult(item);
        }

        // SELECT BY ID
        long lastId = interviewResults.get(interviewResults.size() - 1).getId();
        InterviewResult selectedById = interviewResultDAO.selectById(lastId);
        printInterviewResult(selectedById);

        // DELETE
        System.out.println("try to delete id=" + lastId);
        interviewResultDAO.deleteById(lastId);

        //SELECT ALL
        List<InterviewResult> interviewResultsWithoutDeleted = interviewResultDAO.selectAll();
        for (InterviewResult item :
                interviewResultsWithoutDeleted) {
            printInterviewResult(item);
        }
    }

    private static void printInterviewResult(InterviewResult item) {
        System.out.println("id=" + item.getId() +
                " user_id=" + item.getUserId() +
                " superuser_id=" + item.getSuperUserId() +
                " created_at=" + item.getCreatedAt() +
                " total_rating=" + item.getTotalRating());
    }

    private static void presentInterview(IConnectionFactory factory) throws SQLException {
        InterviewDAO interviewDAO = new InterviewDAO(factory);
        Random rand = new Random();

        final int randNumber = rand.nextInt();

        // INSERT
        Interview interview = new Interview() {{
            setName("" + randNumber);
        }};

        interviewDAO.insert(interview);

        //SELECT ALL
        List<Interview> interviews = interviewDAO.selectAll();
        for (Interview item :
                interviews) {
            printInterview(item);
        }

        // SELECT BY ID
        long lastId = interviews.get(interviews.size() - 1).getId();
        Interview selectedById = interviewDAO.selectById(lastId);
        printInterview(selectedById);

        // DELETE
        System.out.println("try to delete id=" + lastId);
        interviewDAO.deleteById(lastId);

        //SELECT ALL
        List<Interview> interviewsWithoutDeleted = interviewDAO.selectAll();
        for (Interview item :
                interviewsWithoutDeleted) {
            printInterview(item);
        }
    }

    private static void printInterview(Interview item) {
        System.out.println("id=" + item.getId() + " name=" + item.getName() );
    }

    private static void presentSuperUser(IConnectionFactory factory) throws SQLException {
        SuperuserDAO superuserDAO = new SuperuserDAO(factory);
        Random rand = new Random();
        final int randNumber = rand.nextInt();
        
        // INSERT
        Superuser superuser = new Superuser(){{
           setFirstName("" + randNumber );
           setLastName("" + randNumber);
           setMiddleName("" + randNumber);
           setEmail("" + randNumber);
        }};

        superuserDAO.insert(superuser);

        //SELECT ALL
        List<Superuser> superusers = superuserDAO.selectAll();
        for (Superuser item :
                superusers) {
            printSuperUser(item);
        }

        // SELECT BY ID
        long lastId = superusers.get(superusers.size() - 1).getId();
        Superuser selectedById = superuserDAO.selectById(lastId);
        printSuperUser(selectedById);

        // DELETE
        System.out.println("try to delete id=" + lastId);
        superuserDAO.deleteById(lastId);

        //SELECT ALL
        List<Superuser> superusersWithoutDeleted = superuserDAO.selectAll();
        for (Superuser item :
                superusersWithoutDeleted) {
            printSuperUser(item);
        }
    }

    private static void printSuperUser(Superuser superuser){
        System.out.println("id=" + superuser.getId() +
                " firstName= " + superuser.getFirstName() +
                " lastname=" + superuser.getLastName());
    }

    private static void presentUser(IConnectionFactory factory) throws SQLException {
        UserDAO userDao = new UserDAO(factory);
        // INSERT
        User newUser = new User(){{
            Random rand = new Random();

            setBitrix_id(rand.nextLong());
            setLastName("Фамилия");
            setFirstName("Имя");
            setMiddleName("Отчество");
            setBirthDate(new Date(2017, 2, 2));
            setPhone("999123123123");
            setEmail("mail@mail.mail");

        }};
        userDao.insert(newUser);

        // SELECT ALL
        List<User> users = userDao.selectAll();

        for (User user :
                users) {
            printUser(user);
        }


        // SELECT
        User selectedById = userDao.selectById(users.get(users.size() - 1).getId());
        printUser(selectedById);

        // DELETE
        System.out.println("try to delete user with id " + selectedById.getId());
        userDao.deleteById(selectedById.getId());

        // SELECT ALL
        List<User> usersWithoutDeleted = userDao.selectAll();

        for (User user :
                usersWithoutDeleted) {
            printUser(user);
        }
    }

    private static void printUser(User user){
        System.out.println(" id=" + user.getId() +
                " firstName= " + user.getFirstName() +
                " lastname=" + user.getLastName());
    }
}
