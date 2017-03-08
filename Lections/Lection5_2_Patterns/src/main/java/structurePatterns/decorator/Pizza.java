package structurePatterns.decorator;

/**
 * @author Семакин Виктор
 */
public class Pizza implements ComponentInterface {
    @Override
    public void showComponent() {
        System.out.println("Пицца");
    }
}
