package students.controllers.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import students.common.utils.LectionNotificator;
import students.models.pojo.Lection;
import org.apache.log4j.Logger;
import students.services.LectionService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

//начало работы сайта
public class ApplicationLoadListener implements ServletContextListener {

    private static Logger logger = Logger.getLogger(ApplicationLoadListener.class);
    @Autowired
    private LectionService lectionService;
    @Autowired
    private LectionNotificator lectionNotificator;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        autowireLocalBeans(sce);

        notifyByNearestLection();
        startPeriodNotifyAsync();
    }

    private void autowireLocalBeans(ServletContextEvent sce){
        WebApplicationContextUtils
                .getRequiredWebApplicationContext(sce.getServletContext())
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.trace("site was stopped");
    }

    private void startPeriodNotifyAsync(){
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000); // * 60 * 60
                        notifyByNearestLection();
                    } catch (InterruptedException e) {
                        logger.error(e);
                    }
                }
            }
        });

        th1.start();
    }

    private void notifyByNearestLection() {
        List<Lection> lections = lectionService.getNearedLection();
        if (lections.size() > 0) {
            logger.trace("lections founded");
            for (Lection lection :
                    lections) {
               lectionNotificator.notifyByLection(lection);
            }
        } else {
            logger.trace("neared lections not found");
        }
    }
}
