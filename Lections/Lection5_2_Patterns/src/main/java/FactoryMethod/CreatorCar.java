package FactoryMethod;

/**
 * @author Семакин Виктор
 */
public class CreatorCar extends Creator {

    private CreatorCar() {
    }

    public static class CreatorHolder{
        public static final CreatorCar CREATOR_CAR = new CreatorCar();

        public static CreatorCar getInstance(){
            return CREATOR_CAR;
        }
    }

    public Car create() {
        return new Car();
    }
}
