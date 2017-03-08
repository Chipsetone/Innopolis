package structurePatterns.facade;

/**
 * @author Семакин Виктор
 */
public class HumanFacade {
    private Brain brain;
    private Hands hands;
    private Heart heart;

    public HumanFacade(Brain brain, Hands hands, Heart heart) {
        this.brain = brain;
        this.hands = hands;
        this.heart = heart;
    }

    public HumanFacade() {
        this(new Brain(), new Hands(), new Heart());
    }

    public void life(){
        brain.stayCold(100);
        brain.think("8 marta");

        hands.doWork();
        heart.stayHot(120);
        heart.takeRythm();
    }
}
