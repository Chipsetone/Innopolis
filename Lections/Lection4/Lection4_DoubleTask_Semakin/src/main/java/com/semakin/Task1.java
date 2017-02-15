package com.semakin;

/**
 * 1. Напишите программу,
 которая каждую секунду отображает на экране данные о времени,
 прошедшем от начала сессии,
 а другой ее поток выводит сообщение каждые 5 секунд.
 Предусмотрите возможность ежесекундного оповещения потока,
 воспроизводящего сообщение, потоком, отсчитывающим время.
 */
public class Task1 {
    private static final Integer MAGIC_NUMBER = 42;
    MillisecondsCounter msCounter = new MillisecondsCounter(MAGIC_NUMBER);

    public void runTask() throws InterruptedException {
        Thread timeCounter = new Thread(new Runnable() {
            @Override
            public void run() {
                msCounter.countTime();
            }
        });

        timeCounter.start();

        Thread firstThread = new Thread(new TimeNotifier(1000, msCounter, ""));
        firstThread.start();

        Thread secondThread = new Thread(new TimeNotifier(5000, msCounter, "five!"));
        secondThread.start();
    }
}
