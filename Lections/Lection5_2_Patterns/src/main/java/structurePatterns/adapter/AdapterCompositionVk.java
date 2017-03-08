package structurePatterns.adapter;

/**
 * @author Семакин Виктор
 */
public class AdapterCompositionVk implements  Bot {
    private VkBot vkBot = new VkBot();
    private static final int DELAY = 100;

    @Override
    public void sendMessage(String message, int userId) {
        vkBot.sendMessage(message, userId, checkIsFriend());
    }

    @Override
    public void sendSpam(String spam, int people) {
        vkBot.sendSpam(people, spam, getDelay());
    }

    @Override
    public void sleep(float millis) {
        vkBot.sleep();
    }

    private boolean checkIsFriend(){
        return true;
    }

    public int getDelay() {
        return DELAY;
    }
}
