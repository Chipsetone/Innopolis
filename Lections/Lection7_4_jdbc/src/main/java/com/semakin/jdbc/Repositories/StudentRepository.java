package com.semakin.jdbc.Repositories;

import com.semakin.jdbc.DTO.Student;
import com.semakin.jdbc.entitylogic.ConnectionFactory;
import com.semakin.jdbc.entitylogic.EntityRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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


        Connection conn = ConnectionFactory.getInstance().getConnection();
        String sqlQuery = "INSERT INTO student (name, birthdate, sex)" +
                "VALUES(?,?,?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        preparedStatement.setString(1, "Arnold");
        preparedStatement.setDate(2, entity.getBirthDate());
        preparedStatement.setBoolean(3, true);
        preparedStatement.executeUpdate();
    }

    @Override
    public Student selectById(long id) {
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
}
