package students.models.dao;

import org.springframework.stereotype.Component;
import students.common.exceptions.UserDAOException;
import students.models.connector.AcademConnector;
import students.models.pojo.Student;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class StudentDAO {

    private static Logger logger = Logger.getLogger(StudentDAO.class);

    private static final String TABLE_NAME = "PUBLIC.Student";

    private static String SQL_ALL_STUDENTS = "SELECT * FROM " + TABLE_NAME;

    private static String SQL_STUDENTS_GROUP = "SELECT * FROM " + TABLE_NAME + " WHERE id_group = ?";

    private static String SQL_FIND_STUDENT = "SELECT * FROM " + TABLE_NAME + " WHERE id =?";

    private static String SQL_DELETE_STUDENT = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

    private static String SQL_UPDATE_STUDENT = "UPDATE " + TABLE_NAME + "\n" +
            "\tSET id=?, name=?, birthdate=?, sex=?, id_group=?, email = ?" +
            "\tWHERE id=?";

    private static String SQL_INSERT_STUDENT = "INSERT INTO " + TABLE_NAME + "(\n" +
            "\t name, birthdate, sex, id_group, email)\n" +
            "\tVALUES (?, ?, ?, ?,?);";

    public List<Student> getAllStudents(){
        List<Student> studentsList = new ArrayList<>();
        try(Connection connection = AcademConnector.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_ALL_STUDENTS);

            while(resultSet.next()) {
                logger.debug(resultSet.getString("name"));

                Student student = getStudentFromResultSet(resultSet);
                studentsList.add(student);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return studentsList;
    }

    public int deleteStudent(int id) {
        int count = 0;
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_STUDENT)) {
            preparedStatement.setInt(1, id);

            count = preparedStatement.executeUpdate();
            logger.debug(id+" student was deleted");
        } catch (SQLException e) {
            logger.error(e);
        }
        return count;
    }

    public int updateStudent(Student student){

        int count = 0;
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STUDENT)) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setDate(3, new Date(student.getBirthdate().getTime()));
            preparedStatement.setString(4, student.getSex());
            preparedStatement.setInt(5, student.getIdGroup());
            preparedStatement.setInt(6, student.getId());
            preparedStatement.setString(7, student.getEmail());

            count = preparedStatement.executeUpdate();
            logger.debug(student.getId()+" student was update"+student.getIdGroup());
        } catch (SQLException e) {
            logger.error(e);
        }
        return count;
    }

    public int insertStudent(Student student){

        int count = 0;
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_STUDENT)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setDate(2, new Date(student.getBirthdate().getTime()));
            preparedStatement.setString(3, student.getSex());
            preparedStatement.setInt(4, student.getIdGroup());
            preparedStatement.setString(5, student.getEmail());

            count = preparedStatement.executeUpdate();
            logger.debug(student.getId()+" student was insert"+student.getIdGroup());
        } catch (SQLException e) {
            logger.error(e);
        }
        return count;
    }

    public Student getStudentById(int id) throws UserDAOException {

        logger.debug(id);
        Student student = null;
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_STUDENT)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                logger.debug("find"+id);
                student = getStudentFromResultSet(resultSet);
            }else{
                logger.debug(id+" not found");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return student;
    }

    public List<Student> getStudentsByGroup(int groupid){
        List<Student> studentsList = new ArrayList<>();
        try(Connection connection = AcademConnector.getConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement(SQL_STUDENTS_GROUP);
            preparedStatement.setInt(1,groupid);
            ResultSet resultSet = preparedStatement.executeQuery();


            while(resultSet.next()) {
                logger.debug(resultSet.getString("name"));

                Student student = getStudentFromResultSet(resultSet);
                studentsList.add(student);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return studentsList;
    }

    private static Student getStudentFromResultSet(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getDate("birthdate"),
                resultSet.getString("sex"),
                resultSet.getInt("id_group"),
                resultSet.getString("email")
        );
    }
}
