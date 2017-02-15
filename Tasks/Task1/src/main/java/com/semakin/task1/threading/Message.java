package com.semakin.task1.threading;

import com.semakin.task1.exceptions.ThreadMessageException;

/**
 * Сообщение , которое хранит передаваемое значение или исключение
 * @author Виктор Семакин
 */
public class Message {
    private Exception exception;
    private Integer message;
    private String description;

    /**
     * Сообщение, содержащее исключение
     * @param exception
     */
    public Message(Exception exception) {
        this(exception, "");
    }

    /**
     * Сообщение, содержащее исключение с описанием
     * @param exception
     * @param description
     */
    public Message(Exception exception, String description){
        this.exception = exception;
        this.description = description;
    }

    /**
     * Сообщение, содержащее число
     * @param message
     */
    public Message(Integer message) {
        this(message, "");
    }

    /**
     * Сообщение, содержащее число с описанием
     * @param message
     * @param description
     */
    public Message(Integer message, String description) {
        this.message = message;
        this.description = description;
    }

    /**
     * получает число, хранящееся в сообщении
     * @return число
     * @throws ThreadMessageException если в сообщении хранится не число, а исключение
     */
    public Integer getMessage() throws ThreadMessageException{
        if(isInvalidMessage()){
            throw new ThreadMessageException("Невалидное сообщение. Нужно использовать isInvalidMessage()!", getException());
        }

        return message;
    }

    /**
     * Проверяет содержимое сообщения
     * @return true если в сообщении хранится Exception, false - если число
     */
    public boolean isInvalidMessage(){
        return exception != null;
    }

    /**
     * Возвращает исключение, хранящееся в сообщении
     * @return исключение
     */
    public Exception getException(){
        return exception;
    }

    /**
     * @return текст описания, переданный в сообщении
     */
    public String getDescription() {
        return description;
    }
}
