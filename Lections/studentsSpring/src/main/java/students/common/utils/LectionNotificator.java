package students.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import students.models.pojo.Lection;
import students.models.pojo.Student;
import org.apache.log4j.Logger;
import students.services.StudentService;

import java.util.List;


@Service
public class LectionNotificator {
    private static Logger logger = Logger.getLogger(LectionNotificator.class);

    @Autowired
    private StudentService studentService;
    @Autowired
    private Mailer mailer;

    public void notifyByLection(Lection lection){
        List<Student> students =
            studentService.getStudentsByGroupId(lection.getGroupid());
        logger.trace(students.size() + "founded");
        for (Student student:
             students) {
            mailer.sendMail(student.getEmail(),"lection is neared", lection.getSubject());
        }
    }
}
