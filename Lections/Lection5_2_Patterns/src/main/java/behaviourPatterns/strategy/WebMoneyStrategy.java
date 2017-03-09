package behaviourPatterns.strategy;

/**
 * @author Семакин Виктор
 */
public class WebMoneyStrategy extends SendStrategy {
    public WebMoneyStrategy() {
        setPercent(200);
    }

    @Override
    public void sendMoney(int sum) {
        System.out.println("you sent money=" + sum +
                " with fix comission=200 рублей или %");
    }
}
