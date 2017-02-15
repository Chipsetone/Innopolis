package com.semakin;

public class MillisecondsCounter {
    private int interval;
    private int currentTime = 0;
    private final Object lock = new Object();


    public MillisecondsCounter(int milliseconds) {
        this.interval = milliseconds;
    }

    public void countTime(){
        while(true){
            sleep(Thread.currentThread(), interval);

            synchronized (lock) {
                currentTime += interval;
                lock.notifyAll();
            }
        }
    }

    public int getTime(){
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return currentTime;
        }
    }

    private void sleep(Thread thread, Integer sleepTime){
        try {
            thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
