package com.semakin.labs.lab1.threading;

import com.semakin.labs.lab1.ResultPrinter;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @see IMessageQueueProcessorable
 * @see IMessagePushable
 * @author Виктор Семакин
 */
public class MessageQueueProcessor implements IMessageQueueProcessorable, IMessagePushable {
    //TODO готово - перевести с ConcurrentCollection на ReentrantLock

    private Queue<Message> messageQueue = new LinkedList<>();
    private ResultPrinter resultPrinter;
    private int sum = 0;
    private boolean isError = false;
    private Logger logger = Logger.getLogger(MessageQueueProcessor.class);
    private final ReentrantLock lock = new ReentrantLock();
    /**
     * @param resultPrinter отображатель результата
     */
    public MessageQueueProcessor(ResultPrinter resultPrinter) {
        this.resultPrinter = resultPrinter;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void pushMessage(Message message) {
        lock.lock();
        try {
            messageQueue.add(message);
        }
        finally {
            lock.unlock();
        }
    }

    /**
     * Метод не ориентирован на вызов из нескольких потоков!
     * {@inheritDoc }
     */
    @Override
    public void runProcessingMessages() {
        if(isError){
            logger.error("Ошибка - обработка сообщений остановлена.");
            return;
        }
        Message currentMessage = pollMessage();

        while(currentMessage != null){

            if(currentMessage.isInvalidMessage()){

                isError = true;

                String description = currentMessage.getDescription();
                logger.error("Ошибка! " + description);

                Exception exception = currentMessage.getException();
                logger.error(exception.getMessage());
                return;
            }
            int messageValue = currentMessage.getMessage();
            updateAndShowSum(messageValue);

            currentMessage = pollMessage();
        }
        logger.debug("Обработка сообщений опустошила очередь");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isStopped() {
        return isError || isQueueEmpty();
    }

    private boolean isQueueEmpty(){
        lock.lock();
        try {
            return messageQueue.isEmpty();
        }
        finally {
            lock.unlock();
        }
    }

    private Message pollMessage(){
        lock.lock();
        try {
            return messageQueue.poll();
        }
        finally {
            lock.unlock();
        }
    }

    private void updateAndShowSum(int added){
        sum += added;
        resultPrinter.println("" + sum);
    }
}
