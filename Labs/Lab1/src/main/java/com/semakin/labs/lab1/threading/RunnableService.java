package com.semakin.labs.lab1.threading;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Распределитель запуска потоков
 * @author Виктор Семакин
 */
public class RunnableService {
    private ExecutorService service = Executors.newWorkStealingPool(4); // единственное узкое место пока что

    /**
     *Запуск {@link Runnable} на выполнение
     * @param action
     */
    public synchronized void addAction(Runnable action){
        service.submit(action);
    }

    /**
     * Запускает обработку ресурсов в параллельном режиме
     * @param todoList список обработчиков ресурсов
     * @return массив результатов работы каждого обработчика после окончания их обработки
     * @throws InterruptedException при прерывании потока
     */
    public List<Future<Boolean>> invokeAll(List<ResourceCalculator> todoList) throws InterruptedException {
        List<Future<Boolean>> invokeResult = service.invokeAll(todoList);

        return invokeResult;
    }

    /**
     * Расширяет метод {@link RunnableService#invokeAll(List)}
     * Возвращает расчетчик завершения потоков
     * @param todoList
     * @return {@link ThreadsCompleteCalculator}
     * @throws InterruptedException
     */
    public ThreadsCompleteCalculator getInvokeAllResult(List<ResourceCalculator> todoList) throws InterruptedException {
        List<Future<Boolean>> invokeResult = invokeAll(todoList);
        ThreadsCompleteCalculator result = new ThreadsCompleteCalculator(invokeResult);

        return result;
    }

}
