package cretionalPatterns.abstractFactory.ikea;

import cretionalPatterns.abstractFactory.*;

/**
 * @author Семакин Виктор
 */
public class IkeaFactory extends MebelFactory {
    public Chair createChair() {
        return new ChairIkea();
    }

    public Table createTable() {
        return new TableIkea();
    }

    public Sofa createSofa() {
        return new SofaIkea();
    }

    public Taburetka createTaburetka() {
        return new TaburetkaIkea();
    }
}
