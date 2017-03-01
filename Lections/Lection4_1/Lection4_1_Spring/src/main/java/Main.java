import com.semakin.lections.lection41.spring.DataHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Семакин Виктор
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");

        DataHandler dataHandler = (DataHandler)ctx.getBean("dataHandler");

        dataHandler.hadleData("src", "dest");
    }
}
