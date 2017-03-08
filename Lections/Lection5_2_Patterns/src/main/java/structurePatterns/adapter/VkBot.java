package structurePatterns.adapter;

/**
 * @author Семакин Виктор
 */
public class VkBot {
    void sendMessage(String message, int userId, boolean isFriend){
        System.out.println("VK. we sent message to group and user. message=" + message + " isFriend=" + isFriend + " userId= " + userId);
    }

    void sendSpam(int people, String spam, int delay){
        System.out.println("VK. we sent spam to people ");
    }

    void sleep(){
        System.out.println("VK. sleep Not impletemnted");
    }
}
