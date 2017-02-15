package com.semakin.task1.threading;

import com.semakin.task1.ResultPrinter;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @see IMessageQueueProcessorable
 * @see IMessagePushable
 * @author Виктор Семакин
 */
public class MessageQueueProcessor implements IMessageQueueProcessorable, IMessagePushable {
    //TODO перевести с ConcurrentCollection на ReentrantLock
    private ConcurrentLinkedQueue<Message> messageQueue = new ConcurrentLinkedQueue<>();
    private ResultPrinter resultPrinter;
    private int sum = 0;
    private boolean isError = false;

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
        messageQueue.add(message);
    }

    /**
     * Метод не ориентирован на вызов из нескольких потоков!
     * {@inheritDoc }
     */
    @Override
    public void runProcessingMessages() {
        if(isError){
            return;
        }
        Message currentMessage = pollMessage();

        while(currentMessage != null){

            if(currentMessage.isInvalidMessage()){
                isError = true;

                String description = currentMessage.getDescription();
                System.out.println("Ошибка! " + description);

                Exception exception = currentMessage.getException();
                System.out.println(exception.getMessage());
                return;
            }
            int messageValue = currentMessage.getMessage();
            updateAndShowSum(messageValue);

            currentMessage = pollMessage();
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isStopped() {
        return isError || isQueueEmpty();
    }

    private boolean isQueueEmpty(){
        return messageQueue.isEmpty();
    }

    private Message pollMessage(){
        return messageQueue.poll();
    }

    private void updateAndShowSum(int added){
        sum += added;
        resultPrinter.println("" + sum);
    }
}
