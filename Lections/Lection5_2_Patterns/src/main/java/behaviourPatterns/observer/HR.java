package behaviourPatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public class HR implements IObservable {
    private List<IObserver> observers = new ArrayList<>();
    private String name;

    public HR(String name) {
        this.name = name;
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        observers.stream().forEach(observer -> observer.message(name + ": I found some job for you"));
    }
}
