package com.semakin.jdbc.Repositories;

import com.semakin.jdbc.DTO.Student;
import com.semakin.jdbc.entitylogic.ConnectionFactory;
import com.semakin.jdbc.entitylogic.EntityRepository;

import java.sql.*;
import java.util.List;

/**
 * @author Семакин Виктор
 */

public class StudentRepository extends EntityRepository<Student> {
    public StudentRepository() {
        super(Student.class);
    }

    @Override
    public void insert(Student entity) throws SQLException, IllegalAccessException {
        String sqlQuery = "INSERT INTO student (name, birthdate, sex)" +
                "VALUES(?,?,?)";

        PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setDate(2, entity.getBirthDate());
        preparedStatement.setBoolean(3, entity.getSex());
        preparedStatement.executeUpdate();
    }

    @Override
    public Student selectById(long id) throws SQLException {
        //return null;
        String sqlQuery = getSelectByIdQueryString();

        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);//createStatement();
        ResultSet resultSet = statement.getResultSet(); //executeQuery(sqlQuery);

        while(resultSet.next()){
            String readedName = resultSet.getString("name");
            Student student = getStudentFromResultSet(resultSet);
            System.out.println(readedName);
            return student;
        }
        return null;
    }

    @Override
    public List<Student> selectAll() {
        return null;
    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public void deleteById(Student entity) {

    }

    private Student getStudentFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        boolean sex = resultSet.getBoolean("sex");
        Date birthDate = resultSet.getDate("birthDate");
        long id = resultSet.getLong("id");

        return new Student(id, name, birthDate, sex);
    }
}
