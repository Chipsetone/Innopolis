package behaviourPatterns.observer;

/**
 * @author Семакин Виктор
 */
public class Researcher implements IObserver {
    private int id;

    public Researcher(int id) {
        this.id = id;
    }

    @Override
    public void message(String message) {
        System.out.println(message + " " + id);
    }
}
