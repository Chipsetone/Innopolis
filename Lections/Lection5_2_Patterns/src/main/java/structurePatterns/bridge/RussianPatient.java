package structurePatterns.bridge;

/**
 * @author Семакин Виктор
 */
public class RussianPatient extends Patient {
    public RussianPatient(IHospitable hospital) {
        super(hospital);
    }

    @Override
    public void heal() {
        hospital.heal();
    }
}
