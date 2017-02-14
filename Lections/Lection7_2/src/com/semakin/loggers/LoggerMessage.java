package com.semakin.loggers;

/**
 * @author Семакин Виктор
 */

public class LoggerMessage {
    private String message = "";

    public LoggerMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
