package army;

import army.entities.AbstractUnit;
import army.entities.IArmyUnit;
import army.entities.decorator.AmmoUnit;
import army.entities.decorator.BudgetUnit;
import army.entities.decorator.FightersUnit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public class Main {
    public static void main(String[] args) {

        IArmyUnit otdelenie = new AbstractUnit(new ArrayList<>());
        otdelenie = new FightersUnit(otdelenie, 35);
        List<IArmyUnit> otdelenies = getMultipleDeepCopy(otdelenie, 10);

        IArmyUnit vzvod = new AbstractUnit(otdelenies);
        vzvod = new AmmoUnit(vzvod, 42);
        List<IArmyUnit> vzvods = getMultipleDeepCopy(vzvod, 10);

        IArmyUnit rota = new AbstractUnit(vzvods);
        rota = new BudgetUnit(rota, 100);
        List<IArmyUnit> rotas = getMultipleDeepCopy(rota, 10);

        IArmyUnit batalion = new AbstractUnit(rotas);
        List<IArmyUnit> batalions = getMultipleDeepCopy(batalion, 10);

        IArmyUnit chast = new AbstractUnit(batalions);
        List<IArmyUnit> chasts = getMultipleDeepCopy(chast, 10);

        IArmyUnit okryg = new AbstractUnit(chasts);
        List<IArmyUnit> okrygs = getMultipleDeepCopy(okryg, 10);

        IArmyUnit army = new AbstractUnit(okrygs);


        printState(army);
    }

    private static List<IArmyUnit> getMultipleDeepCopy(IArmyUnit unit, int copysCount) {

        List<IArmyUnit> units = new LinkedList<>();
        for (int i = 0; i < copysCount; i++) {
            units.add(unit.getCopy());
        }

        return units;
    }

    private static void printState(IArmyUnit armyUnit) {
        int fightersCount = armyUnit.getFightersCount();
        int ammunitionCount = armyUnit.getAmmunitionCount();
        int budget = armyUnit.getBudget();

        System.out.println("Количество бойцов: " + fightersCount);
        System.out.println("Количество аммуниции: " + ammunitionCount);
        System.out.println("Бюджет: " + budget);
    }
}
