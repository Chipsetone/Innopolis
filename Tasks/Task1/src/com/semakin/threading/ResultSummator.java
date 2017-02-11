package com.semakin.threading;

import com.semakin.ResultPrinter;
import com.semakin.exceptions.InnerResourceException;

@Deprecated
public class ResultSummator{

    private int resultSum;
    private ResultPrinter resultPrinter;
    private boolean isFail = false;

    public ResultSummator(ResultPrinter resultPrinter)
    {
        resultSum = 0;
        this.resultPrinter = resultPrinter;
    }

    public synchronized void addResult(int sum) throws InnerResourceException {
        checkFail();
        resultSum += sum;
        log("Сумма по ресурсу: " + sum + " Итого: " + resultSum);
    }

    public synchronized void fail(){
        isFail = true;
    }

    public Integer getResult() throws InnerResourceException {
        checkFail();
        return resultSum;
    }

    private synchronized void checkFail() throws InnerResourceException {
        if(isFail){
            throw new InnerResourceException("Вызов исключения для " + Thread.currentThread().getName());
        }
    }

    private void log(String message){
        resultPrinter.println(message);
    }
}
