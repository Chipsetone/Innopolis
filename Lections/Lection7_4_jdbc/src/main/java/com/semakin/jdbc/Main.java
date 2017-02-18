package com.semakin.jdbc;

import com.semakin.jdbc.DTO.Student;
import com.semakin.jdbc.Repositories.StudentRepository;
import com.semakin.jdbc.entitylogic.ConnectionFactory;

import java.sql.*;
import java.util.List;

/** До воскресения 23:55
 Задание:
 Сделать методы для работы с БД "Студенты"
 Для каждой сущности- добавление, обновление, удаление, запрос по id, запрос по имени, запрос всего списка
 вывести все результаты на консоль.

 * @author Семакин Виктор
 */

public class Main {
    // настройки коннекта к БД в ConnectinoFactory
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        presentStudentWork();
//        StudentRepository repo = new StudentRepository();
//        System.out.println("***\nINSERT Student");
//        Student insertingStudent = new Student(30, "Somebody inserted", new Date(2017,02,17), 'M');
//        repo.insert(insertingStudent);
//        return;


    }

    private static void presentStudentWork() throws IllegalAccessException, SQLException, NoSuchFieldException {
        StudentRepository repo = new StudentRepository();

        // INSERT
        System.out.println("***\nINSERT Student");
        String insertingName = "Somebody inserted";
        Student insertingStudent = new Student(insertingName, new Date(2017,02,17), 'M');
        repo.insert(insertingStudent);

        // SELECT ALL
        System.out.println("***\nSELECT All students" );
        List<Student> allStudents = repo.selectAll();
        for (Student stud :
                allStudents) {
            printStudent(stud);
        }

        int lastStudentArrayIndex = allStudents.size() - 1;
        long lastStudentId = allStudents.get(lastStudentArrayIndex).getId();
        //UPDATE
        System.out.println("***\nUPDATE By student with id " + lastStudentId);
        Student updatedStudent = allStudents.get(lastStudentArrayIndex);
        updatedStudent.setName("some another name");
        repo.update(updatedStudent);

        // SELECT BY ID
        System.out.println("***\nSELECT By id " + lastStudentId);
        Student studSelectedById = repo.selectById(lastStudentId);
        printStudent(studSelectedById);

        // SELECT BY NAME
        System.out.println("***\nSELECT By Name " + insertingName );
        List<Student> allStudentsWithName = repo.selectByName(insertingName);
        for (Student stud :
                allStudentsWithName) {
            printStudent(stud);
        }

        // DELETE BY ID
        System.out.println("***\nDELETE BY Id " + lastStudentId );
        repo.deleteById(lastStudentId);


        // SELECT ALL FOR check
        System.out.println("***\nSELECT All for check deleting:" );
        List<Student> allStudentsWithoutDeleted = repo.selectAll();
        for (Student stud :
                allStudentsWithoutDeleted) {
            printStudent(stud);
        }

    }

    private static void printStudent(Student stud){
        System.out.println("id= " + stud.getId() +
                " name= '" + stud.getName() +
                "' birthdate= " + stud.getBirthDate() +
                " sex = " + stud.getSex());
    }
}
