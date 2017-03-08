package army.entities.decorator;

import army.entities.IArmyUnit;

/**
 * @author Семакин Виктор
 */
public class FightersUnit extends DecoratorComponent implements IArmyUnit {
    private int fightersCount;
    public FightersUnit(IArmyUnit armyCalculator, int fightersCount) {
        super(armyCalculator);
        this.fightersCount = fightersCount;
    }

    @Override
    public int getFightersCount() {
        return super.getFightersCount() + fightersCount;
    }

    @Override
    public IArmyUnit getCopy() {
        return new FightersUnit(super.getCopy(), fightersCount);
    }
}
