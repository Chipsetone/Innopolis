package students.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import students.common.exceptions.UserDAOException;
import students.models.dao.StudentDAO;
import students.models.pojo.Student;
import students.services.StudentService;
import students.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class EditServlet extends ContextInitServlet {
    private static Logger logger = Logger.getLogger(RegistrationServlet.class);

    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentDAO studentDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            userService.authorise("login", "123");
            // херня какая то. хз зачем
        } catch (UserDAOException e) {
            logger.error(e);
        }
        Student student = null;

        try {
            student = studentDAO.getStudentById(id);
            logger.debug(student.getId());
        } catch (UserDAOException e) {
            logger.error(e);
        }
        req.setAttribute("id", student.getId());
        req.setAttribute("name", student.getName());
        req.setAttribute("birthdate", student.getBirthdate());
        req.setAttribute("sex", student.getSex());
        req.setAttribute("group", student.getIdGroup());

        req.setAttribute("student", student);
        req.getRequestDispatcher("/editStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on edit");
        String strId = req.getParameter("id");
        int id = (strId.equals(""))?0:Integer.parseInt(req.getParameter("id"));
        Student student = new Student();
        student.setId(id);
        student.setName(req.getParameter("name"));
        student.setBirthdate(Date.valueOf(req.getParameter("birthdate")));
        student.setSex(req.getParameter("sex"));
        student.setIdGroup(Integer.parseInt(req.getParameter("group")));
        int count = 0;
        if (id == 0) {
            count = studentService.insertStudent(student);
        } else {
            count = studentService.updateStudentOnId(student);
        }
        if (count != 0) {
            logger.trace("true");
            resp.sendRedirect("/students/list");
        } else {
            logger.trace("false");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
