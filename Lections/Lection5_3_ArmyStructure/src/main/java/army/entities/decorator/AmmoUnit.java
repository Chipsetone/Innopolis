package army.entities.decorator;

import army.Copyable;
import army.entities.IArmyUnit;

/**
 * @author Семакин Виктор
 */
public class AmmoUnit extends DecoratorComponent implements Copyable<IArmyUnit>{
    private int ammunitionCount;

    public AmmoUnit(IArmyUnit armyCalculator, int ammunitionCount) {
        super(armyCalculator);
        this.ammunitionCount = ammunitionCount;
    }

    @Override
    public int getAmmunitionCount() {
        return super.getAmmunitionCount() + ammunitionCount;
    }

    @Override
    public IArmyUnit getCopy() {
        return new AmmoUnit(super.getCopy(), ammunitionCount);
    }
}
