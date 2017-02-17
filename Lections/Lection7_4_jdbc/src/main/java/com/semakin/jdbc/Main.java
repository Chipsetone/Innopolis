package com.semakin.jdbc;

import com.semakin.jdbc.DTO.Student;
import com.semakin.jdbc.Repositories.StudentRepository;
import com.semakin.jdbc.entitylogic.ConnectionFactory;
import com.semakin.jdbc.entitylogic.SQLQueryFactory;

import java.sql.*;

/** До воскресения 23:55
 Задание:
 Сделать методы для работы с БД "Студенты"
 Для каждой сущности- добавление, обновление, удаление, запрос по id, запрос по имени, запрос всего списка
 вывести все результаты на консоль.

 * @author Семакин Виктор
 */

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException {
        Student stud = new Student(1, "Арни", new Date(2017, 02, 16), true);

        StudentRepository studentRepository = new StudentRepository();
        studentRepository.insert(stud);

        Student student = studentRepository.selectById(0);
//        SQLQueryFactory factory = new SQLQueryFactory();
//        factory.InsertObjectIntoDb(stud);
//        insertDb();
        //selectDb();
    }

    private static void selectDb(){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

            while(resultSet.next()){
                String readedName = resultSet.getString("name");
                System.out.println(readedName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertDb() throws SQLException {
        Connection conn = ConnectionFactory.getInstance().getConnection();
            String sqlQuery = "INSERT INTO student (name, birthdate, sex)" + //, id_group)" +
                    "VALUES(?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, "Arnold");
            preparedStatement.setDate(2, new Date(1978, 2,5));
            preparedStatement.setBoolean(3, true);
            //preparedStatement.setInt(4, 1);
            preparedStatement.executeUpdate();
    }

}
