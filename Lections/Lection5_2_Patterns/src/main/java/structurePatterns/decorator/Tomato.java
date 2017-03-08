package structurePatterns.decorator;

public class Tomato extends PizzaComponent{

    public Tomato(ComponentInterface componentInterface) {
        super(componentInterface);
    }

    @Override
    public void showComponent() {
        super.showComponent();
        System.out.println("Tomato");
    }
}

