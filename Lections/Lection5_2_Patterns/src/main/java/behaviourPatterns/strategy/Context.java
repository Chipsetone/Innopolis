package behaviourPatterns.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public class Context {
    private SendStrategy currentStrategy;
    private List<SendStrategy> availableStrategies = new ArrayList<>();

    public void sendMoney(int sum){
        findStrategy();
        currentStrategy.sendMoney(sum);
    }

    private void findStrategy(){
        SendStrategy tempStrategy = new BankStrategy();
        tempStrategy.setPercent(100);
        tempStrategy.setTime(200);

        for (SendStrategy strategy :
                availableStrategies) {
            if (strategy.getTime() > 4) {
                continue;
            }
            if (strategy.getPercent() < tempStrategy.getPercent()) {
                tempStrategy = strategy;
            }
        }

        currentStrategy = tempStrategy;
    }


    public void addStrategy(SendStrategy strategy) {
        availableStrategies.add(strategy);
    }
}
