package behaviourPatterns.strategy;

/**
 * @author Семакин Виктор
 */
public class MailStrategy extends SendStrategy {
    public MailStrategy() {
        setTime(3);
    }

    @Override
    public void sendMoney(int sum) {
        System.out.println("you sent sum=" + sum
                + " with percent=" + getPercent()
                + " and delays three days");

    }
}
