package behaviourPatterns.memento;

/**
 * @author Семакин Виктор
 */
public class CareTaker {
    private Memento taker;

    public Memento getTaker() {
        return taker;
    }

    public void setTaker(Memento taker) {
        this.taker = taker;
    }
}
