package structurePatterns.adapter;

/**
 * @author Семакин Виктор
 */
public interface Bot {
    void sendMessage(String message, int userId);
    void sendSpam(String spam, int people);
    void sleep(float millis);

}
