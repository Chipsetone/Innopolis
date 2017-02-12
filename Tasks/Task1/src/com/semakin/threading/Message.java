package com.semakin.threading;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.exceptions.ThreadMessageException;

/**
 * Сообщение, которое хранит передаваемое значение или исключение
 */
public class Message {
    private Exception resourceException;
    private Integer message;
    private String description;

    public Message(Exception resourceException) {
        this(resourceException, "");
    }

    public Message(Exception resourceException, String description){
        this.resourceException = resourceException;
        this.description = description;
    }

    public Message(Integer message) {
        this(message, "");
    }

    public Message(Integer message, String description) {
        this.message = message;
        this.description = description;
    }

    public Integer getMessage() {
        if(isInvalidMessage()){
            throw new ThreadMessageException("Невалидное сообщение. Нужно использовать isInvalidMessage()!", getException());
        }

        return message;
    }

    /**
     * Проверяет содержимое сообщения
     * @return true если в сообщении хранится Exception
     */
    public boolean isInvalidMessage(){
        return resourceException != null;
    }

    /**
     * Возвращает исключение, хранящееся в сообщении
     * @return исключение
     */
    public Exception getException(){
        return resourceException;
    }

    public String getDescription() {
        return description;
    }
}
