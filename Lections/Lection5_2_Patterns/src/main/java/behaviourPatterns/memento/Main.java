package behaviourPatterns.memento;

/**
 * @author Семакин Виктор
 */
public class Main {
    public static void main(String[] args) {
        Level level = new Level(){{
            setState("level 1");
        }};

        CareTaker careTaker = new CareTaker();
        careTaker.setTaker(level.saveState());

        level.setState("level 2");
        System.out.println(level.getState());

        level.restoreState(careTaker.getTaker());
        System.out.println(level.getState());
    }
}
