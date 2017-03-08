package cretionalPatterns.abstractFactory.mnogoMebeli;

import cretionalPatterns.abstractFactory.*;

/**
 * @author Семакин Виктор
 */
public class MnogoMebeliFactory extends MebelFactory {
    public Chair createChair() {
        return new ChairMnogoMebeli();
    }

    public Table createTable() {
        return new TableMnogoMebeli();
    }

    public Sofa createSofa() {
        return new SofaMnogoMebeli();
    }

    public Taburetka createTaburetka() {
        return new TaburetkaMnogoMebeli();
    }
}
