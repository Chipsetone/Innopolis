package structurePatterns.decorator;

/**
 * @author Семакин Виктор
 */
public class Cheese extends PizzaComponent{
    public Cheese(ComponentInterface componentInterface) {
        super(componentInterface);
    }

    @Override
    public void showComponent() {
        super.showComponent();
        System.out.println("Cheese");
    }
}

