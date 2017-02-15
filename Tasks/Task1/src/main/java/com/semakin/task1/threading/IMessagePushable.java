package com.semakin.task1.threading;

/**
 * Заталкиватель сообщений в очередь обработки
 * @author Виктор Семакин
 */
public interface IMessagePushable {
    /**
     * Добавить сообщение в очередь обработки
     * @param message сообщение
     */
    void pushMessage(Message message);
}
