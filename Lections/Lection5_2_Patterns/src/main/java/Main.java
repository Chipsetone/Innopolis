import cretionalPatterns.factoryMethod.CreatorCar;
import cretionalPatterns.factoryMethod.CreatorTrain;
import cretionalPatterns.factoryMethod.Creator;
import structurePatterns.adapter.AdapterCompositionVk;
import structurePatterns.adapter.AdapterGeneralizeTelegram;
import structurePatterns.adapter.Bot;
import structurePatterns.decorator.Cheese;
import structurePatterns.decorator.Mushrooms;
import structurePatterns.decorator.Pizza;
import structurePatterns.decorator.Tomato;

import java.util.ArrayList;

/**
 * @author Семакин Виктор
 */
public class Main {
    public static void main(String[] args) {

        //factoryMethodTest();
//        decoratorMethoTest();
        adapterMethodTest();
    }

    private static void adapterMethodTest() {
        Bot bot1 = new AdapterGeneralizeTelegram();
        Bot bot2 = new AdapterCompositionVk();

        bot1.sendMessage("фыавфыв", 35);
        bot1.sendSpam("spam", 2);
        bot1.sleep(33.3f);
        System.out.println("");

        bot2.sendMessage("message", 34);
        bot2.sendSpam("spam", 22);
        bot2.sleep(45.5f);
    }


    private static void decoratorMethoTest(){
        Pizza pizza = new Pizza();

        Mushrooms mushrooms = new Mushrooms(pizza);
        Tomato tomato = new Tomato(mushrooms);
        Cheese cheese = new Cheese(tomato);

        cheese.showComponent();
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
