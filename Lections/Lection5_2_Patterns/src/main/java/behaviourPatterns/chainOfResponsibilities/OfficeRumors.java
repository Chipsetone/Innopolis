package behaviourPatterns.chainOfResponsibilities;

/**
 * @author Семакин Виктор
 */
public class OfficeRumors extends Rumors {
    @Override
    void writeRumors(String message) {
        System.out.println("plankton said: " + message);
    }
}
