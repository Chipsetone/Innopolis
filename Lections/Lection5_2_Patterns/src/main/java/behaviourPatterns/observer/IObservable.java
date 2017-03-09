package behaviourPatterns.observer;

/**
 * @author Семакин Виктор
 */
public interface IObservable {
    void addObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void notifyAllObservers();
}
