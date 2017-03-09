package behaviourPatterns.chainOfResponsibilities;

/**
 * @author Семакин Виктор
 */
public abstract class Rumors {
    protected Rumors rumors;

    public boolean isTrue = false;

    public Rumors setNextRumors(Rumors nextRumors) {
        this.rumors = nextRumors;

        return rumors;
    }

    abstract void writeRumors(String message);

    public void chain(String message) {
        if (isTrue) {
            writeRumors(message);
            return;
        }

        if (this.rumors != null) {
            this.rumors.chain(message);
        }
    }
}
