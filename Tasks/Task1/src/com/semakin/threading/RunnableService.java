package com.semakin.threading;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Chi on 06.02.2017.
 */
public class RunnableService {

    private ExecutorService service = Executors.newWorkStealingPool(5);//newCachedThreadPool();

    @Deprecated
    public synchronized void addAction(Runnable action){
        service.submit(action);
    }

    public List<Future<Boolean>> invokeAll(List<ResourceCalculator> todoList) throws InterruptedException {

        return service.invokeAll(todoList);
    }

}
