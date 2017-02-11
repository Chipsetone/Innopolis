package com.semakin;

import com.semakin.exceptions.InnerResourceException;

/**
 * Created by Chi on 06.02.2017.
 */
public class ResultSumKeeper {
    private int resultSum;
    private LogPrinter logPrinter;
    private boolean isFail = false;

    public ResultSumKeeper(LogPrinter logPrinter)
    {
        resultSum = 0;
        this.logPrinter = logPrinter;
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
        logPrinter.println(message);
    }
}
