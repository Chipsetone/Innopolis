package behaviourPatterns.strategy;

/**
 * @author Семакин Виктор
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context();

        SendStrategy strategy = new BankStrategy();
        strategy.setPercent(1);
        strategy.setTime(1);
        SendStrategy strategy1 = new MailStrategy();
        strategy1.setPercent(300);
        SendStrategy strategy2 = new WebMoneyStrategy();
        strategy2.setPercent(2)
        ;
        context.addStrategy(strategy);
        context.addStrategy(strategy1);
        context.addStrategy(strategy2);

        context.sendMoney(200);
    }
}
