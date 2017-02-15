package com.semakin;

/**
 *2. Не внося изменений в код потока-"хронометра" ,
 * добавьте еще один поток,
 * который выводит на экран другое сообщение каждые 7 секунд.
 * Предполагается использование методов wait(), notifyAll().
 */
public class Task2 extends Task1{
    @Override
    public void runTask() throws InterruptedException {

        super.runTask();

        Thread secondThread = new Thread(new TimeNotifier(7000, this.msCounter, "seven!"));
        secondThread.start();
    }
}
