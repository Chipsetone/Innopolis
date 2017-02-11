package com.semakin.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Chi on 06.02.2017.
 */
public class RunnableService {

    private ExecutorService service = Executors.newCachedThreadPool();

    public synchronized void runAction(Runnable action){
        service.submit(action);
    }

    public void terminateAllThreads(){
        service.shutdown();
    }
}
