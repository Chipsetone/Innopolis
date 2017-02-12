package com.semakin.threading;

import com.semakin.ResultPrinter;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 */
public class MessageProcessor implements  IMessageProcessorable, IMessagePushable {
    private ConcurrentLinkedQueue<Message> messageQueue = new ConcurrentLinkedQueue<>();
    private ResultPrinter resultPrinter;
    private volatile int sum = 0;
    private boolean isError = false;

    public MessageProcessor(ResultPrinter resultPrinter) {
        this.resultPrinter = resultPrinter;
    }

    @Override
    public void pushMessage(Message message) {
        messageQueue.add(message);
    }

    /**
     * Метод не ориентирован на вызов из нескольких потоков!
     * запускает процесс расчета доступных элементов очереди
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

    @Override
    public int getSum() {
        return sum;
    }

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
