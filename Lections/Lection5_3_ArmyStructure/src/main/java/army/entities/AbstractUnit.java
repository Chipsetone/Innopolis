package army.entities;

import army.Copyable;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public class AbstractUnit implements IArmyUnit{
    protected List<IArmyUnit> subUnits;

    public AbstractUnit(List<IArmyUnit> subUnits) {
        this.subUnits = subUnits;
    }

    public int getFightersCount() {
        int fightrerCount = 0;

        for (IArmyUnit unit :
                subUnits) {
            fightrerCount += unit.getFightersCount();
        }

        return fightrerCount;
    }

    public int getAmmunitionCount() {
        int ammunitionCount = 0;

        for (IArmyUnit unit :
                subUnits) {
            ammunitionCount += unit.getAmmunitionCount();
        }

        return ammunitionCount;
    }

    public int getBudget() {
        int budget = 0;

        for (IArmyUnit unit :
                subUnits) {
            budget += unit.getBudget();
        }

        return budget;
    }

    public IArmyUnit getCopy() {
        List<IArmyUnit> subUnitsCopy = new LinkedList<IArmyUnit>();

        for (IArmyUnit subUnit :
                subUnits) {
            subUnitsCopy.add(subUnit.getCopy());
        }

        return new AbstractUnit(subUnitsCopy);
    }
}
