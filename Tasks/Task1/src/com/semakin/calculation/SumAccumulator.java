package com.semakin.calculation;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.parsers.StringConverter;
import com.semakin.validation.ResourceSymbols;

/**
 * Created by Chi on 10.02.2017.
 */
class SumAccumulator{
    private int sumResult = 0;
    private String strAsNumberBuffer = "";
    private StringConverter stringConverter;

    public SumAccumulator(StringConverter stringConverter){
        this.stringConverter = stringConverter;
    }

    protected void processSymbol(char symbol) throws InnerResourceException {
        checkAllowedChar(symbol);

        if(symbol == ResourceSymbols.space){
            completeCalculations();
        }else{
            strAsNumberBuffer += symbol;
        }
    }

    protected void completeCalculations() throws InnerResourceException {
        if(strAsNumberBuffer.length() > 0){
            pullBuffer();
        }
        else{
            return;
        }
    }

    private void pullBuffer() throws InnerResourceException {
        int number = stringConverter.toInt(strAsNumberBuffer);
        sumResult += number;
        strAsNumberBuffer = "";
    }

    protected int getResult(){
        return sumResult;
    }

    private void checkAllowedChar(char symbol) throws InnerResourceException {
        if (!(ResourceSymbols.allowedSymbols.contains(symbol) ||
                isDigitChar(symbol))){
            throw new InnerResourceException("Недопустимый символ '" + symbol + "'");
        }
    }

    private boolean isDigitChar(char symbol){
        return Character.isDigit(symbol);
    }
}
