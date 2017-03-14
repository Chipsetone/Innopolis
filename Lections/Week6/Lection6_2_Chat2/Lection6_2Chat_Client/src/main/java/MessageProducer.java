import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Семакин Виктор
 */
public class MessageProducer implements Runnable {
    private String appId;

    public MessageProducer(String appId) {
        this.appId = appId;
    }

    public void run() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://10.240.17.94:61616"); //tcp://localhost:61616");
        try {
            Connection myConnection = factory.createConnection();
            myConnection.start();

            Session session = myConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            Destination destination = session.createQueue("Dest");

            Topic destination = session.createTopic("chat");
            javax.jms.MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            sendMessages(session, producer);

            session.close();
            myConnection.close();


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void sendMessages(Session session, javax.jms.MessageProducer producer) throws JMSException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while(shouldContinue()) {
                String messageContent = br.readLine();
                String mes = appId + ":" + messageContent;
                TextMessage textMessage = session.createTextMessage(mes);
                producer.send(textMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean shouldContinue(){
        return true;
    }
}
