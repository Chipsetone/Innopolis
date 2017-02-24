package com.semakin.labs.lab2.dbrestore;

import java.util.HashSet;

/**
 * @author Семакин Виктор
 */
public final class InsertedObjects {
    private HashSet<Long> insertedObjects;
    private final Object locker = new Object();
    private boolean isStopped = false;
    private final Object isStoppedLocker = new Object();

    public InsertedObjects() {
        this.insertedObjects = new HashSet<>();
    }

    public boolean isInserted(Long objectId){
        synchronized (locker) {
            return insertedObjects.contains(objectId);
        }
    }

    public void markAsInserted(Long objectId){
        synchronized (locker) {
            insertedObjects.add(objectId);
        }
    }

    public boolean isStopped() {
        synchronized (isStoppedLocker) {
            return isStopped;
        }
    }

    public void setStopped(boolean stopped) {
        synchronized (isStoppedLocker) {
            isStopped = stopped;
        }
    }
}
