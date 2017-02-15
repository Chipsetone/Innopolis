/**
 * @author Семакин Виктор
 */
public class Main {

    public static void main(String[] args) {
        Animal animal = new Animal("beaver");

        System.out.println("do something in main");

        animal.eat();
        animal.doCrap();
        animal.sleep();

    }
}
