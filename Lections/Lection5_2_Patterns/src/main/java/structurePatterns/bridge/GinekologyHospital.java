package structurePatterns.bridge;

/**
 * @author Семакин Виктор
 */
public class GinekologyHospital implements IHospitable {
    @Override
    public void heal() {
        System.out.println("GinekologyHospital: 8th March");
    }
}
