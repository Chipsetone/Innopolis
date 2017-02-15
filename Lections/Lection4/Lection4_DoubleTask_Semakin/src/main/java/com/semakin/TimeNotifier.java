package com.semakin;

/**
 * Created by Chi on 09.02.2017.
 */
public class TimeNotifier implements Runnable {
    private final int interval;
    MillisecondsCounter ms;
    String threadMessage;

    public TimeNotifier(final int millisecondsToNotify, MillisecondsCounter msCounter, String threadMessage){
        interval = millisecondsToNotify;
        this.ms = msCounter;
        this.threadMessage = threadMessage;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        String threadName = currentThread.getName();

        int prevTime = ms.getTime();
        System.out.println(threadName + " init with time: " + prevTime);
        while(true){
            int passedTime = ms.getTime();

            if ((passedTime - prevTime) >= interval) {
                prevTime = passedTime;
                System.out.println(interval + " " + threadName + " time: " + passedTime + " " + threadMessage);
            }
        }
    }
}
