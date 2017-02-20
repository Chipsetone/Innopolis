package com.semakin.labs.lab1tests.unit.threading;

import com.semakin.labs.lab1.threading.ThreadsCompleteCalculator;
import com.semakin.labs.lab1.threading.ThreadsCompleteType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Семакин Виктор
 */
class ThreadsCompleteCalculatorTest {
    //TODO - готово - здесь.  в этом классе тестов дописать на завершение потоков
    //TODO проверить остановку всех потоков при ошибочных данных в одном из потоков. логика программы это подразумевает, но это нетестируемо.
    @Test
    void isComplete_normalThreadExecuting_complete() {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Callable<Boolean>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable<Boolean> callable = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    System.out.println("ждем " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                    System.out.println("подождали " + Thread.currentThread().getName());
                    return true;
                }
            };

            callables.add(callable);
        }
        ThreadsCompleteType expected = ThreadsCompleteType.complete;
        List<Future<Boolean>> futureResults = new ArrayList<>();
        try {
            futureResults = service.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("ошибки то не было. потоки на вход скормлены нормальные");
        }

        ThreadsCompleteCalculator completeResult = new ThreadsCompleteCalculator(futureResults);

        completeResult.calcThreadCompletes();
        ThreadsCompleteType actualCompleteResultType = completeResult.getCompleteResult();

        assertEquals(expected, actualCompleteResultType);
    }

    @Test
    void isComplete_InterruptionException_error() {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Callable<Boolean>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable<Boolean> callable = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    System.out.println("ждем " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                    System.out.println("подождали " + Thread.currentThread().getName());
                    return true;
                }
            };

            callables.add(callable);
        }

        Callable<Boolean> interruptedCallable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Thread.sleep(1000);
                throw new InterruptedException("Здесь будет ошибка");
            }
        };
        callables.add(interruptedCallable);

        ThreadsCompleteType expected = ThreadsCompleteType.error;
        List<Future<Boolean>> futureResults = new ArrayList<>();
        try {
            futureResults = service.invokeAll(callables);
        } catch (InterruptedException e) {
            System.out.println("обещанная ошибка поймана");
        }

        ThreadsCompleteCalculator completeResult = new ThreadsCompleteCalculator(futureResults);
        completeResult.calcThreadCompletes();

        ThreadsCompleteType actualCompleteResultType = completeResult.getCompleteResult();

        assertEquals(expected, actualCompleteResultType);
    }

    @Test
    void isComplete_threadReturnFalse_error() {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Callable<Boolean>> callables = new ArrayList<>();
        ThreadsCompleteType expected = ThreadsCompleteType.error;

        Callable<Boolean> interruptedCallable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Thread.sleep(100);
                return false;
            }
        };
        callables.add(interruptedCallable);
        List<Future<Boolean>> futureResults = new ArrayList<>();
        try {
            futureResults = service.invokeAll(callables);
        } catch (InterruptedException e) {
            fail("всё нормально должно пройти. Исключение должно обработаться внутри расчетчика");
        }

        ThreadsCompleteCalculator completeResult = new ThreadsCompleteCalculator(futureResults);
        completeResult.calcThreadCompletes();
        ThreadsCompleteType actualCompleteResultType = completeResult.getCompleteResult();

        assertEquals(expected, actualCompleteResultType);
    }
}