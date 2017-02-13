/**
 *  Artem Pervushow, [13.02.17 09:17]
 Вариант 1:
 Реализовать программу из 2-х потоков.
 Один из потоков каждую секунду генерирует случайное число в интервале [0;99].
 Второй поток раз в пять секунд выводит количество уникальных чисел,
 сгенерированных первым потоком.
 После того, как будет сгенерировано все 100 чисел, оба потока должны остановить свое выполнение.
 * @author Семакин Виктор
 */
public class Application {
    private MessageQueue messageQueue = new MessageQueue();
    private MillisecondsCounter msCounter = new MillisecondsCounter(1000);


    public void run(){
        // запуск расчетчика времени
        // запуск считывателя очереди
        // запуск добавлятора в очередь

        Thread threadCounter = new Thread(new Runnable() {
            @Override
            public void run() {
                msCounter.countTime();
            }
        });
        threadCounter.start();

        Thread thread2Generator = new Thread1Generator(msCounter, messageQueue);
        thread2Generator.start();


        Thread thread3UniqueChecker = new Thread2UniqueChecker(msCounter, messageQueue);
        thread3UniqueChecker.start();
    }


    public boolean isStopped() {
        throw new UnsupportedOperationException();
    }
}
