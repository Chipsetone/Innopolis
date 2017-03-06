package students.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import students.models.dao.StudentDAO;
import students.models.pojo.Student;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    public List<Student> getAllStudents(){
        return studentDAO.getAllStudents();
    }

    public int deleteStudentOnId(int id){

        return studentDAO.deleteStudent(id);
    }

    public int updateStudentOnId(Student student){

        return studentDAO.updateStudent(student);
    }

    public int insertStudent(Student student){

        return studentDAO.insertStudent(student);
    }

    public List<Student> getStudentsByGroupId(int groupid){
        return studentDAO.getStudentsByGroup(groupid);
    }

}
