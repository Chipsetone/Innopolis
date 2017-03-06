package students.controllers.listeners;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import students.common.exceptions.UserDAOException;
import students.common.utils.Mailer;
import students.models.dao.UserDAO;
import students.models.pojo.User;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//начало работы сессии
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    private static Logger logger = Logger.getLogger(SessionListener.class);

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Mailer mailer;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.trace(se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.trace(se.getSession().getAttribute("name"));
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if ("id".equals(event.getName())&&event.getValue()!=null){
            try {
                logger.trace("mail is ok");
                User user = userDAO.getUserById((Integer) event.getValue());
//                mailer.sendMail(user.getEmail(),"youlogined","wow");
                throw new UnsupportedOperationException("Эту хрень надо доделать");
            } catch (UserDAOException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }

}
