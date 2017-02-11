package com.test.semakin;

import com.semakin.LogPrinter;
import com.semakin.ResultSumKeeper;
import com.semakin.exceptions.InnerResourceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**
 * Created by Chi on 06.02.2017.
 */
class ResultSumKeeperTest {
    @Test
    void getResult() throws InnerResourceException {
        ResultSumKeeper sumPrinter = getNewResultSumKeeper();
        int expectedOnInit = 0;
        int expectedAfterAdding = 4;

        int actualOnInit = sumPrinter.getResult();
        sumPrinter.addResult(4);
        int actualAfterAdding = sumPrinter.getResult();


        Assertions.assertEquals(expectedOnInit, actualOnInit);
        Assertions.assertEquals(expectedAfterAdding, actualAfterAdding);
    }

    @Test
    void addResult() throws InnerResourceException {
        ResultSumKeeper sumPrinter = getNewResultSumKeeper();
        int expected = 5;

        sumPrinter.addResult(5);
        int actual = sumPrinter.getResult();

        Assertions.assertEquals(expected, actual);
    }

    void addResult_Fail_Exception(){
        ResultSumKeeper sumPrinter = getNewResultSumKeeper();
        sumPrinter.fail();
        int someNumber = 5;
        try {
            sumPrinter.addResult(someNumber);
        } catch (InnerResourceException e) {
            e.printStackTrace();
        }
    }

    private ResultSumKeeper getNewResultSumKeeper(){
        return new ResultSumKeeper(getLogPrinter());
    }

    private LogPrinter getLogPrinter(){
        return new LogPrinter();
    }
}