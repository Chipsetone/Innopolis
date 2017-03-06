package students.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import students.models.pojo.Lection;
import students.services.LectionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LectionsServlet extends ContextInitServlet {
    private static Logger logger = Logger.getLogger(LectionsServlet.class);

    @Autowired
    private LectionService lectionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lection> lections= lectionService.getAllLections();
        logger.debug(lections.size());
        req.setAttribute("lections", lections);
        req.getRequestDispatcher("/lections.jsp").forward(req, resp);;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
