package cretionalPatterns.abstractFactory;

/**
 * @author Семакин Виктор
 */
public abstract class MebelFactory {

    public abstract Chair createChair();
    public abstract Table createTable();
    public abstract Sofa createSofa();
    public abstract Taburetka createTaburetka();
}
