package army.entities;

import army.Copyable;

/**
 * @author Семакин Виктор
 */
public interface IArmyUnit extends Copyable<IArmyUnit> {
    int getFightersCount();
    int getAmmunitionCount();
    int getBudget();
}
