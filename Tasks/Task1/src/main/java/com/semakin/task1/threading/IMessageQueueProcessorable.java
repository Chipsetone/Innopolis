package com.semakin.task1.threading;

/**
 * Обработчик очереди сообщений
 * @author Виктор Семакин
 */
public interface IMessageQueueProcessorable {
    /**
     * Запуск процесса расчета очереди сообщений
     * Работает до тех пор пока очередь не достигнет опустошения
     */
    void runProcessingMessages();

    /**
     * Проверяет отработала ли очередь
     * @return true - очередь отбработана и остановлена
     */
    boolean isStopped();
}
