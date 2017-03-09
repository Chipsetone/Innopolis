package behaviourPatterns.chainOfResponsibilities;

/**
 * @author Семакин Виктор
 */
public class TeschaRumors extends Rumors {
    @Override
    void writeRumors(String message) {
        System.out.println("beast said: " + message);
    }
}
