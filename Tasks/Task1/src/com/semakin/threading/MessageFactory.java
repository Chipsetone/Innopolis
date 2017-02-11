package com.semakin.threading;

/**
 * Created by Chi on 11.02.2017.
 */
public class MessageFactory {
    public Message newValidMessage(int value){
        return new Message(value);
    }

    public Message newInvalidMessage(Exception exception){
        return new Message(exception);
    }
}
