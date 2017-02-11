package com.semakin.threading;

import com.semakin.exceptions.ThreadMessageException;

/**
 * Сообщение, которое хранит передаваемое значение или исключение
 */
public class Message {
    private Exception resourceException;
    private Integer message;

    public Message(Exception resourceException) {
        this.resourceException = resourceException;
    }

    public Message(Integer message) {
        this.message = message;
    }

    public Integer getMessage(){
        if(isInvalidMessage()){
            throw new ThreadMessageException("Невалидное сообщение. Пользуйтесь isInvalidMessage()!", getException());
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
}
