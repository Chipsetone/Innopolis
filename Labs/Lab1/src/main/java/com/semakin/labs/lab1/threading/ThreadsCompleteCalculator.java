package com.semakin.labs.lab1.threading;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Результат выполнения List<Callable<Boolean>>
 *
 * @author Семакин Виктор
 */
public class ThreadsCompleteCalculator {
    private static final Logger logger = Logger.getLogger(ThreadsCompleteCalculator.class);

    private ThreadsCompleteType completeResult = ThreadsCompleteType.processing;
    private List<Future<Boolean>> threadsCompleteResult = new ArrayList<>();
    private final ReentrantLock isCompleteLock = new ReentrantLock();

    /**
     * Инициализатор.
     * @param observableFutures массив Future<Boolean>>,
     *                          по которым будет считаться результат выполнения создавших их потоков
     */
    public ThreadsCompleteCalculator(List<Future<Boolean>> observableFutures){
        threadsCompleteResult = observableFutures;
    }

    /**
     * Расчет выполнения потоков
     * Внимание! Операция будет ожидать выполнения всех скормленных объекту потоков!
     */
    public void calcThreadCompletes(){
        List<Boolean> futureCompleteResult = new ArrayList<>();

        for (Future<Boolean> futureResult :
                threadsCompleteResult) {
            try {
                boolean result = futureResult.get();
                if (result == false) {
                    logger.error("При завершении потока ожидался результат true");
                    setIsComplete(ThreadsCompleteType.error);

                    break;
                }
                futureCompleteResult.add(result);
            } catch (InterruptedException e) {
                logger.error("Ошибка при выполнении потока", e);
                setIsComplete(ThreadsCompleteType.error);
                return;
            } catch (ExecutionException e) {
                logger.error("Ошибка при выполнении потока", e);
                setIsComplete(ThreadsCompleteType.error);
                return;
            }
        }

        checkComplete(futureCompleteResult);
    }

    private void checkComplete(List<Boolean> callableResults){
        if(callableResults.size() != threadsCompleteResult.size()){
            logger.error("ошибка. обратитесь к разработчику");
            setIsComplete(ThreadsCompleteType.error);
            return;
        }

        for (Boolean isDoneCallable :
                callableResults) {
            if (!isDoneCallable) {
                logger.error("Ожидался ответ от потока - true");
                setIsComplete(ThreadsCompleteType.error);
                return;
            }
        }

        setIsComplete(ThreadsCompleteType.complete);
    }

    /**
     * @return {@link ThreadsCompleteType} результат расчета завершенности потоков
     */
    public ThreadsCompleteType getCompleteResult(){
        isCompleteLock.lock();
        try {
            return completeResult;
        }
        finally {
            isCompleteLock.unlock();
        }
    }

    private void setIsComplete(ThreadsCompleteType value){
        isCompleteLock.lock();
        try{
            completeResult = value;
        }
        finally {
            isCompleteLock.unlock();
        }
    }

}
