package structurePatterns.decorator;

public class Mushrooms extends PizzaComponent{
    public Mushrooms(ComponentInterface componentInterface) {
        super(componentInterface);
    }

    @Override
    public void showComponent() {
        super.showComponent();
        System.out.println("Mushrooms");
    }
}
