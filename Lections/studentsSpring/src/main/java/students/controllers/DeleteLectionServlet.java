package students.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import students.services.LectionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteLectionServlet extends ContextInitServlet {

    private static Logger logger = Logger.getLogger(DeleteLectionServlet.class);
    @Autowired
    private LectionService lectionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (lectionService.deleteLectioOnId(Integer.parseInt(id)) > 0) {
            logger.trace(req.getParameter("id") + " was deleted");
            resp.sendRedirect("/students/lections");
        } else {
            logger.trace(id + " not deleted");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
