package com.semakin.threading;

/**
 * Created by Chi on 11.02.2017.
 */
public interface IMessageProcessorable{//} extends IMessagePushable {

    void runProcessingMessages();

    Message getLastMessage();

    int getSum();

    boolean isStopped();
}
