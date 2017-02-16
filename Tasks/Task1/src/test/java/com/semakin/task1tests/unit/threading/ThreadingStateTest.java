package com.semakin.task1tests.unit.threading;

import com.semakin.task1.threading.RunnableService;
import com.semakin.task1.threading.ThreadingState;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Семакин Виктор
 */
class ThreadingStateTest {
    @Test
    void isDone_LongOperations_False() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Callable<Boolean>> longTasks = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            Callable<Boolean> task = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    Thread.currentThread().sleep(2000);
                    System.out.println(Thread.currentThread() + " завершается");
                    return true;
                }
            };
            longTasks.add(task);
        }

        List<Future<Boolean>> futureResult = service.invokeAll(longTasks);
        ThreadingState state = new ThreadingState(futureResult);

        Boolean actualIsDone = state.isDone();
        System.out.println(Thread.currentThread() + " главный поток");
        assertFalse(actualIsDone);
    }

    @Test
    void isDone_WaitAllOperations_True() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Callable<Boolean>> quickTasks = new LinkedList<>();
        final int countOfQuickTasks = 2;

        for (int i = 0; i < countOfQuickTasks; i++) {
            Callable<Boolean> task = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return true;
                }
            };
            quickTasks.add(task);
        }

        List<Future<Boolean>> futureResult = service.invokeAll(quickTasks);
        ThreadingState state = new ThreadingState(futureResult);

        final int msGuaranteedCompleteTasks = 1000;
        Thread.currentThread().sleep(1000);
        Boolean actualIsDone = state.isDone();

        assertTrue(actualIsDone);
    }
}