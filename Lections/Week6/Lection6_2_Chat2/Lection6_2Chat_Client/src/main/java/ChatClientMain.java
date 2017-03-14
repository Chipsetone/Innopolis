/**
 * @author Семакин Виктор
 */
public class ChatClientMain {
    public static void main(String[] args) {
        String appId = "Chi";

        Thread thread1 = new Thread(new MessageConsumer(appId));
        thread1.start();

        Thread thread2 = new Thread(new MessageProducer(appId));
        thread2.start();
    }
}
