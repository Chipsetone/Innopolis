package behaviourPatterns.strategy;

/**
 * @author Семакин Виктор
 */
public abstract class SendStrategy {
    private int time;
    private int percent;

    abstract void sendMoney(int sum);

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
