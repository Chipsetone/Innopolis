package cretionalPatterns.objectPool;

import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Семакин Виктор
 */
public abstract class ObjectPool<T> implements IObjectPool<T>{
    private static final Logger logger = Logger.getLogger(ObjectPool.class);

    private int maxPoolSize;
    private int optimalPoolSize;

    private ConcurrentLinkedQueue<T> busyObjects;
    private ConcurrentLinkedQueue<T> freeObjects;

    private final Object releaseLocker = new Object();

    public ObjectPool(int maxPoolSize, int optimalPoolSize, ConcurrentLinkedQueue<T> freeObjects) {
        this.maxPoolSize = maxPoolSize;
        this.freeObjects = freeObjects;
        this.optimalPoolSize = optimalPoolSize;

        busyObjects = new ConcurrentLinkedQueue<T>();
    }

    @Override
    public T getInstance() throws PoolOverflowException {
        synchronized (releaseLocker) {
            if (busyObjects.size() >= maxPoolSize) {
                freeObjects.clear();
                logger.error("В пуле нет свободных записей! Ожидание свободной записи(");
                try {
                    releaseLocker.wait();
                    return getInstanceDirect();
                } catch (InterruptedException e) {
                    logger.error("Не удалось долждаться особбождения объекта из пула", e);
                }
            }
        }

        synchronized (releaseLocker){
            int busySize = busyObjects.size();

            if(busySize > optimalPoolSize){
                logger.info("Занятость пула превысила оптимальную отметку!");
            }

            if(busySize < maxPoolSize){
                return getInstanceDirect();
            }
        }

        throw new PoolOverflowException("Что-то пошло не так и не удалось получить объект из пула");
    }

    private T getInstanceDirect(){
        T obj = freeObjects.poll();
        busyObjects.add(obj);

        return obj;
    }

    @Override
    public void release(T object) {
        synchronized (releaseLocker){
            if(busyObjects.contains(object)){
                freeObjects.add(object);
                busyObjects.remove(object);
                releaseLocker.notify();
            }
        }
    }

}
