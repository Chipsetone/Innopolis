package objectPool;

/**
 * @author Семакин Виктор
 */
public class PoolOverflowException extends Exception{
    public PoolOverflowException(String message) {
        super(message);
    }
}
