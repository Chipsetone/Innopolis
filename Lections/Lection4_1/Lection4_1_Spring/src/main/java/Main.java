import com.semakin.lections.lection41.spring.AppConfig;
import com.semakin.lections.lection41.spring.DataHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.crypto.Data;

/**
 * @author Семакин Виктор
 */
public class Main {
    public static void main(String[] args) {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
//
//        DataHandler dataHandler = (DataHandler)ctx.getBean("dataHandler");

//        DataHandler dataHandler = new DataHandler();
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        DataHandler dataHandler = (DataHandler)context.getBean("dataHandler");
        dataHandler.hadleData("src", "dest");
    }
}
