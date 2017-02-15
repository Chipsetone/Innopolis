package com.semakin.task1.exceptions;

/**
 * Created by Chi on 11.02.2017.
 */
public class ThreadMessageException extends RuntimeException{
    public ThreadMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
