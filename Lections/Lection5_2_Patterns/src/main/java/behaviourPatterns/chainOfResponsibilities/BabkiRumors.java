package behaviourPatterns.chainOfResponsibilities;

/**
 * @author Семакин Виктор
 */
public class BabkiRumors extends Rumors {
    @Override
    void writeRumors(String message) {
        if (isManMessage(message)) {
            message += "... наркоман наверное";
        }
        else{
            message += "... куртизанка";
        }

        System.out.println("babki said: " + message);
    }

    private boolean isManMessage(final String message){
        return (message.length() <= 100);
    }
}
