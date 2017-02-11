package com.semakin.threading;

import com.semakin.ResultPrinter;

import java.util.concurrent.ConcurrentLinkedQueue;


public class MessageProcessor implements  IMessageProcessorable {
    private ConcurrentLinkedQueue<Message> messageQueue = new ConcurrentLinkedQueue<>();
    private ResultPrinter resultPrinter;
    private int sum = 0;
    private boolean isError = false;
    private Message lastMessage;


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
                lastMessage = currentMessage;
                return;
            }
            int messageValue = currentMessage.getMessage();
            updateAndPrintSum(messageValue);

            lastMessage = currentMessage;
            currentMessage = pollMessage();
        }
    }

    @Override
    public Message getLastMessage() {
        return lastMessage;
    }

    private Message pollMessage(){
        return messageQueue.poll();
    }

    private void updateAndPrintSum(int added){
        sum += added;
        resultPrinter.println("" + sum);
    }
}
