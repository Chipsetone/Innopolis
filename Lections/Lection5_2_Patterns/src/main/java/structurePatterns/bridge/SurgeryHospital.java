package structurePatterns.bridge;

/**
 * @author Семакин Виктор
 */

public class SurgeryHospital implements IHospitable {

    @Override
    public void heal() {
        System.out.println("SurgeryHospital: chic-chic");
    }
}
