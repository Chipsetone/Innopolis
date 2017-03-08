package army.entities.decorator;

import army.entities.IArmyUnit;

/**
 * @author Семакин Виктор
 */
public abstract class DecoratorComponent implements IArmyUnit {
    protected IArmyUnit armyUnit;

    public DecoratorComponent(IArmyUnit armyUnit) {
        this.armyUnit = armyUnit;
    }

    public int getFightersCount() {
        return armyUnit.getFightersCount();
    }

    public int getAmmunitionCount() {
        return armyUnit.getAmmunitionCount();
    }

    public int getBudget() {
        return armyUnit.getBudget();
    }

    @Override
    public IArmyUnit getCopy() {
        return armyUnit.getCopy();
    }
}
