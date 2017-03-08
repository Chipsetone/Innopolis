package structurePatterns.decorator;

/**
 * @author Семакин Виктор
 */
public abstract class PizzaComponent implements ComponentInterface {
    protected ComponentInterface componentInterface;

    public PizzaComponent(ComponentInterface componentInterface) {
        this.componentInterface = componentInterface;
    }

    @Override
    public void showComponent() {
        componentInterface.showComponent();
    }
}
