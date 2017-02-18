package com.semakin.jdbc.Repositories;

import com.semakin.jdbc.DTO.Student;
import com.semakin.jdbc.entitylogic.GodEntityRepository;

/**
 * @author Семакин Виктор
 */
public class StudentRepository extends GodEntityRepository<Student> {
    public StudentRepository() {
        super(Student.class);
    }
}
