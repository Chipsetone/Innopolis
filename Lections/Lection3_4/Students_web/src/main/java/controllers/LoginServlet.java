package controllers;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Семакин Виктор
 */
public class LoginServlet extends HttpServlet{
//    private static final String loggerConfigFile = "resources/log4j.xml";
//
//    static {
//        PropertyConfigurator.configure("resources/log4j.xml");
//    }
    private static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("students.login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (UserService.authorize(login, password)) {
            logger.info("authorized");
            resp.sendRedirect("/list");
        }
        else{
            logger.info("unauthorized");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
