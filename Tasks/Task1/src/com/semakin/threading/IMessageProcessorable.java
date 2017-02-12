package com.semakin.threading;

/**
 * Обработчик очереди сообщений
 * @author Виктор Семакин
 */
public interface IMessageProcessorable{//} extends IMessagePushable {

    /**
     * Запуск обработки сообщений.
     * Работает до тех пор пока очередь не достигнет опустошения
     * @author Виктор Семакин
     */
    void runProcessingMessages();

    int getSum();

    boolean isStopped();
}
