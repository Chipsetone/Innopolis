import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Семакин Виктор
 */
public class MessageConsumer implements Runnable {
    private String appId;

    public MessageConsumer(String appId) {
        this.appId = appId;
    }

    public void run() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://10.240.17.94:61616");//"tcp://localhost:61616");
        try {
            Connection myConnection = factory.createConnection();
            myConnection.start();

            Session session = myConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            Destination destination = session.createQueue("Dest");
            Topic destination = session.createTopic("chat");
            javax.jms.MessageConsumer messageConsumer = session.createConsumer(destination);

            waitForMessages(messageConsumer);


            session.close();
            myConnection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void waitForMessages(javax.jms.MessageConsumer messageConsumer) throws JMSException {
        while(shouldReceive()) {
            Message message = messageConsumer.receive();

            String messageComplex = ((TextMessage) message).getText();
            printReceivedData(messageComplex);
        }
    }

    private boolean printReceivedData(String messageComplex) {
        System.out.println(messageComplex);
        return false;
    }


    private boolean shouldReceive(){
        return true;
    }

    public String getAppId() {
        return appId;
    }
}
