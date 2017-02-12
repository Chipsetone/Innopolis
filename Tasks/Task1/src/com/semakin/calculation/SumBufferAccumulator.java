package com.semakin.calculation;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.parsers.StringConverter;
import com.semakin.threading.IMessagePushable;
import com.semakin.threading.Message;
import com.semakin.validation.ValidSymbols;

/**
 * Буфер символов.
 * @author Виктор Семакин
 */
public class SumBufferAccumulator {
    private int sumResult = 0;
    private String strAsNumberBuffer = "";
    private StringConverter stringConverter;
    private IMessagePushable messagePusher;
    private boolean isStopped = false;
    private String resourceAddress;

    public SumBufferAccumulator(StringConverter stringConverter, IMessagePushable messagePusher, String resourceAddress){
        this.stringConverter = stringConverter;
        this.messagePusher = messagePusher;
        this.resourceAddress = resourceAddress;
    }

    /**
     * Добавляет символ в обработчик. Последним вызовом буфера должно быть освобождение буфера tryReleaseBuffer()
     * TODO перевести всё на finalize и использовать try-with-resources - понять как работает
     * @param symbol
     */
    public void processSymbol(char symbol) {
        if(isStopped){
            return;
        }

        if(isNotAllowedChar(symbol)){
            Message message = new Message(new InnerResourceException("Недопустимый символ '" + symbol + "' ресурс: " + resourceAddress));
            messagePusher.pushMessage(message);
            isStopped = true;
            return;
        }

        if(symbol == ValidSymbols.space){
            tryReleaseBuffer();
        }else{
            strAsNumberBuffer += symbol;
        }
    }

    /**
     * Освобождает буфер
     */
    public void tryReleaseBuffer() {
        if(strAsNumberBuffer.length() > 0){
            pullBuffer();
        }
        else{
            return;
        }
    }

    private void pullBuffer(){
        Message message;
        try {
            // TODO вытащить сюда проверку и конвертацию когда-нибудь
            int number = stringConverter.toInt(strAsNumberBuffer);
            if(number == 0){
                releaseBuffer();
                return;
            }
            message = new Message(number, strAsNumberBuffer);
        } catch (InnerResourceException e) {
            message = new Message(e, "ресурс: " + resourceAddress);
            isStopped = true;
        }

        messagePusher.pushMessage(message);
        releaseBuffer();
    }

    private boolean isNotAllowedChar(char symbol) {
        return (!(ValidSymbols.allowedSymbols.contains(symbol) ||
                Character.isDigit(symbol)));
    }

    private void releaseBuffer(){
        strAsNumberBuffer = "";
    }
}
