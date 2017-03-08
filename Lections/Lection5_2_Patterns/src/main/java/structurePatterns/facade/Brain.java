package structurePatterns.facade;

/**
 * @author Семакин Виктор
 */
public class Brain {
    void think(String thought) {
        System.out.println("Brain: do think about: " + thought);
    }

    void stayCold(int temperature){
        System.out.println("Brain: stay cold temperature: " + temperature);
    }
}
