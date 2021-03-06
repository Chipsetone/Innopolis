import cretionalPatterns.factoryMethod.CreatorCar;
import cretionalPatterns.factoryMethod.CreatorTrain;
import cretionalPatterns.factoryMethod.Creator;
import structurePatterns.adapter.AdapterCompositionVk;
import structurePatterns.adapter.AdapterGeneralizeTelegram;
import structurePatterns.adapter.Bot;
import structurePatterns.bridge.GinekologyHospital;
import structurePatterns.bridge.Patient;
import structurePatterns.bridge.RussianPatient;
import structurePatterns.bridge.SurgeryHospital;
import structurePatterns.decorator.Cheese;
import structurePatterns.decorator.Mushrooms;
import structurePatterns.decorator.Pizza;
import structurePatterns.decorator.Tomato;
import structurePatterns.facade.HumanFacade;

import java.util.ArrayList;

/**
 * @author Семакин Виктор
 */
public class Main {
    public static void main(String[] args) {

        //factoryMethodTest();
//        decoratorMethodTest();
//        adapterMethodTest();
//        facadeMethodTest();
        bridgeMethodTest();
    }

    private static void bridgeMethodTest() {
        Patient kyleRease = new RussianPatient(new SurgeryHospital());
        Patient sarahKonor = new RussianPatient(new GinekologyHospital());

        Patient[] patients = {kyleRease, sarahKonor};

        for (Patient patient :
                patients) {
            patient.heal();

        }
    }

    private static void facadeMethodTest() {
        HumanFacade human = new HumanFacade();
        human.life();
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


    private static void decoratorMethodTest(){
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
