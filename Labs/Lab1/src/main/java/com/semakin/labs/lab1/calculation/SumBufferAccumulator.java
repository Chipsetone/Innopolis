package com.semakin.labs.lab1.calculation;

import com.semakin.labs.lab1.exceptions.InnerResourceException;
import com.semakin.labs.lab1.parsers.StringConverter;
import com.semakin.labs.lab1.threading.IMessagePushable;
import com.semakin.labs.lab1.threading.Message;
import com.semakin.labs.lab1.validation.ValidSymbols;
import org.apache.log4j.Logger;

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

    public static final Logger logger = Logger.getLogger(SumBufferAccumulator.class);

    /**
     * @param stringConverter преобразователь слова буфера в число
     * @param messagePusher заталкиватель подготовленных сообщений в очередь
     * @param resourceAddress адрес ресурса
     */
    public SumBufferAccumulator(StringConverter stringConverter, IMessagePushable messagePusher, String resourceAddress){
        this.stringConverter = stringConverter;
        this.messagePusher = messagePusher;
        this.resourceAddress = resourceAddress;
    }

    /**
     * Добавляет символ в обработчик. Последним вызовом буфера должно быть освобождение буфера tryReleaseBuffer()
     * TODO перевести всё на finalize и использовать try-with-resources - понять как это всё работает здесь
     * @param symbol
     */
    public void pushToBufferOrRelease(char symbol) {
        if(isStopped){
            return;
        }

        if(isNotAllowedChar(symbol)){
            final String forbiddenSymbolMessage = "Недопустимый символ '" + symbol + "' ресурс: " + resourceAddress;
            logger.error(forbiddenSymbolMessage);

            Message message = new Message(new InnerResourceException(forbiddenSymbolMessage));
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
            pullBuffer(strAsNumberBuffer);
        }
        return;
    }

    private void pullBuffer(String convertableToInteger){
        Message message;
        try {
            // TODO вытащить сюда отдельно проверку и конвертацию когда-нибудь
            int number = stringConverter.toInt(convertableToInteger);
            if(number == 0){
                releaseBuffer();
                return;
            }
            message = new Message(number, convertableToInteger);
        } catch (InnerResourceException e) {
            String messageText = "ресурс: " + resourceAddress;
            logger.error("ошибка " + messageText);
            message = new Message(e, messageText);
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
