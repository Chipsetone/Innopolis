package com.semakin.exceptions;

/**
 * Created by Chi on 08.02.2017.
 */
public class InnerResourceException extends Exception {
    public InnerResourceException(String message, Exception cause){
        super(message, cause);
    }

    public InnerResourceException(String message){
        super(message);
    }

    public InnerResourceException(Exception ex){
        super(ex);
    }
}
