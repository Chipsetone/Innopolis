package tests.unit.threading;

import com.semakin.ResultPrinter;
import com.semakin.threading.ResultSummator;
import com.semakin.exceptions.InnerResourceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**
 * Created by Chi on 06.02.2017.
 */
class ResultSummatorTest {
    @Test
    void getResult() throws InnerResourceException {
        ResultSummator sumPrinter = getNewResultSumKeeper();
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
        ResultSummator sumPrinter = getNewResultSumKeeper();
        int expected = 5;

        sumPrinter.addResult(5);
        int actual = sumPrinter.getResult();

        Assertions.assertEquals(expected, actual);
    }

    void addResult_Fail_Exception(){
        ResultSummator sumPrinter = getNewResultSumKeeper();
        sumPrinter.fail();
        int someNumber = 5;
        try {
            sumPrinter.addResult(someNumber);
        } catch (InnerResourceException e) {
            e.printStackTrace();
        }
    }

    private ResultSummator getNewResultSumKeeper(){
        return new ResultSummator(getLogPrinter());
    }

    private ResultPrinter getLogPrinter(){
        return new ResultPrinter();
    }
}