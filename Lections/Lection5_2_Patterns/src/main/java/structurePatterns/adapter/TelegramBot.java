package structurePatterns.adapter;

/**
 * @author Семакин Виктор
 */
public class TelegramBot {

    void sendMessage(String message, int groupId, int userId){
        System.out.println("TG. we sent message to group and user. message=" + message + " groupId=" + groupId + " userId= " + userId);
    }

    void sendSpam(int people, String spam){
        System.out.println("TG. we sent spam to people ");
    }

    void sleep(int millis){
        System.out.println("TG. we sleep for millis: " + millis);
    }
}
