package com.semakin.calculation;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.parsers.StringConverter;
import com.semakin.threading.IMessagePushable;
import com.semakin.threading.Message;
import com.semakin.validation.ResourceSymbols;

/**
 * Created by Chi on 10.02.2017.
 */
public class SumAccumulator{
    private int sumResult = 0;
    private String strAsNumberBuffer = "";
    private StringConverter stringConverter;
    private IMessagePushable messagePusher;
    private boolean isStopped = false;
    private String resourceAddress;

    public SumAccumulator(StringConverter stringConverter, IMessagePushable messagePusher, String resourceAddress){
        this.stringConverter = stringConverter;
        this.messagePusher = messagePusher;
        this.resourceAddress = resourceAddress;
    }

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

        if(symbol == ResourceSymbols.space){
            completeCalculations();
        }else{
            strAsNumberBuffer += symbol;
        }
    }

    public void completeCalculations() {
        if(strAsNumberBuffer.length() > 0){
            pullBuffer();
        }
        else{
            return;
        }
    }

    private int getResult(){
        return sumResult;
    }

    private void pullBuffer(){
        Message message;
        try {
            int number = stringConverter.toInt(strAsNumberBuffer);
            if(number == 0){
                clearBuffer();
                return;
            }
            message = new Message(number, strAsNumberBuffer);
        } catch (InnerResourceException e) {
            message = new Message(e, "ресурс: " + resourceAddress);
            isStopped = true;
        }

        messagePusher.pushMessage(message);
        clearBuffer();
    }

    private boolean isNotAllowedChar(char symbol) {
        return (!(ResourceSymbols.allowedSymbols.contains(symbol) ||
                Character.isDigit(symbol)));
    }

    private void clearBuffer(){
        strAsNumberBuffer = "";
    }
}
