package structurePatterns.facade;

/**
 * @author Семакин Виктор
 */
public class Heart {
    void takeRythm(){
        System.out.println("Heart: tuk-tuk");
    }

    void stayHot(int temperature){
        System.out.println("Heart: out temperature " + temperature);
    }
}
