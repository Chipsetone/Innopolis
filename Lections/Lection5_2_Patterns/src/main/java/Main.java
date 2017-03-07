import FactoryMethod.CreatorCar;
import FactoryMethod.CreatorTrain;
import FactoryMethod.Creator;

import java.util.ArrayList;

/**
 * @author Семакин Виктор
 */
public class Main {
    public static void main(String[] args) {
        factoryMethodTest();
    }

    private static void factoryMethodTest() {
        ArrayList<Creator> creators = new ArrayList<Creator>();

        creators.add(CreatorCar.CreatorHolder.getInstance());
        creators.add(CreatorTrain.CreatorHolder.getInstance());

        for (Creator creator: creators){
            System.out.println();
        }
    }
}
