package structurePatterns.adapter;

/**
 * @author Семакин Виктор
 */
public class AdapterGeneralizeTelegram extends TelegramBot implements Bot {
    @Override
    public void sendMessage(String message, int userId) {
        super.sendMessage(message, getGroupId(), userId);
    }

    @Override
    public void sendSpam(String spam, int people) {
        super.sendSpam(people, spam);
    }

    @Override
    public void sleep(float millis) {
        super.sleep((int) millis);
    }

    private int getGroupId(){
        return 1;
    }
}
