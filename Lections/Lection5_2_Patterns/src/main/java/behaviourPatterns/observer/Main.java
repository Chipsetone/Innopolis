package behaviourPatterns.observer;

/**
 * @author Семакин Виктор
 */
public class Main {
    public static void main(String[] args) {
        HR hr1 = new HR("Маша Лукашевич");
        HR hr2 = new HR("Оля В");

        Researcher r1 = new Researcher(1);
        Researcher r2 = new Researcher(2);
        Researcher r3 = new Researcher(3);
        Researcher r4 = new Researcher(4);
        Researcher r5 = new Researcher(5);

        hr1.addObserver(r1);
        hr1.addObserver(r2);
        hr1.addObserver(r3);

        hr2.addObserver(r3);
        hr2.addObserver(r4);
        hr2.addObserver(r5);

        hr1.notifyAllObservers();
        hr2.notifyAllObservers();
    }
}
