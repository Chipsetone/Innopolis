package structurePatterns.bridge;

/**
 * @author Семакин Виктор
 */
public abstract class Patient {
    protected IHospitable hospital;

    protected Patient(IHospitable hospital) {
        this.hospital = hospital;
    }

    public abstract void heal();
}
