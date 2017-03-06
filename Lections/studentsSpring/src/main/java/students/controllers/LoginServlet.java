package students.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import students.services.UserService;

@Controller
public class LoginServlet{ // extends ContextInitServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value="/students/login", method = RequestMethod.GET)
    public String showLoginPage(){ //HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("doGet");
//        req.getRequestDispatcher("/login.jsp").forward(req,resp);

        return "login";
    }

//
//    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        logger.trace("doPost");
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//        try {
//            logger.trace("try userService.authorise");
//            User user = userService.authorise(login,password);
//            if (user.getId()!=0) {
//                req.getSession().setAttribute("id",user.getId());
//                logger.trace("auth");
//                resp.sendRedirect("/students/list");
//            } else {
//                logger.trace("noauth");
//                req.getRequestDispatcher("/login.jsp").forward(req,resp);
//            }
//        } catch (UserDAOException e) {
//            logger.error(e);
//            resp.sendRedirect("/students/error.jsp");
//        }
//    }
}
