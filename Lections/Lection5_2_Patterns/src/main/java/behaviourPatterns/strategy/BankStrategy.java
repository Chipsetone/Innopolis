package behaviourPatterns.strategy;

/**
 * @author Семакин Виктор
 */
public class BankStrategy extends SendStrategy {
    @Override
    public void sendMoney(int sum) {
        System.out.println("you sent sum=" + sum +
                " with percent=" + getPercent());
    }

}
