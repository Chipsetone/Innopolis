package cretionalPatterns.objectPool;

/**
 * @author Семакин Виктор
 */
public interface IObjectPool<T> {
    T getInstance() throws PoolOverflowException;

    void release(T object);
}
