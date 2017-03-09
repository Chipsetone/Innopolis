package behaviourPatterns.memento;

/**
 * @author Семакин Виктор
 */
public class Level {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public Memento saveState(){
        return new Memento(this.getState());
    }

    public void restoreState(Memento memento) {
        setState(memento.getState());
    }
}
