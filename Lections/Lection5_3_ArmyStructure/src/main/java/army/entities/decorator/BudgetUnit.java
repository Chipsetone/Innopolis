package army.entities.decorator;

import army.Copyable;
import army.entities.IArmyUnit;

/**
 * @author Семакин Виктор
 */
public class BudgetUnit extends DecoratorComponent implements Copyable<IArmyUnit> {
    private int budget;
    public BudgetUnit(IArmyUnit armyCalculator, int budget) {
        super(armyCalculator);
        this.budget = budget;
    }

    @Override
    public int getBudget() {
        return super.getBudget() + budget;
    }

    @Override
    public IArmyUnit getCopy() {
        return new BudgetUnit(super.getCopy(), budget);
    }
}
