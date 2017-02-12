package com.semakin.threading;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Распределитель запуска потоков
 * @author Виктор Семакин
 */
public class RunnableService {
    private ExecutorService service = Executors.newCachedThreadPool(); // newWorkStealingPool(8);

    /**
     *
     * @param action
     */
    @Deprecated
    public synchronized void addAction(Runnable action){
        service.submit(action);
    }

    /**
     * Запускает обработку ресурсов в параллельном режиме
     * @param todoList список обработчиков ресурсов
     * @return массив результатов работы каждого обработчика после окончания их обработки
     * @throws InterruptedException
     */
    public List<Future<Boolean>> invokeAll(List<ResourceCalculator> todoList) throws InterruptedException {

        return service.invokeAll(todoList);
    }

}
