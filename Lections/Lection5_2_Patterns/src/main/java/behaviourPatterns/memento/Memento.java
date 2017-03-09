package behaviourPatterns.memento;

/**
 * @author Семакин Виктор
 */
public class Memento {
    private final String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
