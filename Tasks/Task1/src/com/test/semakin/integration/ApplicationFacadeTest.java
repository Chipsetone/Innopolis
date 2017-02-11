package com.test.semakin.integration;

import com.semakin.ApplicationFacade;
import com.semakin.LogPrinter;
import com.semakin.ResultSumKeeper;
import com.semakin.exceptions.InnerResourceException;
import com.semakin.resourceGetters.ReaderGetterDecorator;
import com.semakin.resourceGetters.ReaderGetterable;
import com.semakin.resourceGetters.ResourceStringGetter;
import com.test.semakin.mocks.ReaderGetterMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Chi on 08.02.2017.
 */
class ApplicationFacadeTest {
    @Test
    void run_Valid() throws InnerResourceException {
        HashMap<String, String> resourcesStub = new HashMap<String, String>(){{
            put("abc","1 1 1 1 1 1 1 1 ");
            put("def","2 0 2 5 6 4 57");
            put("ghi","1 2 3 4 5 6 7 8 9 7");
            put("abcdefef","0 1 -1 2 -5 7 -13 24");
            put("87gu","1 2 3 4");
            put("qwert","2");
            put("кирилица","1");
        }};
        Integer expectedSum = 68;
        LogPrinter logPrinter = getNewLogPrinter();
        ResultSumKeeper sumKeeper = new ResultSumKeeper(logPrinter);

        runApp(resourcesStub, sumKeeper);
        waitThreads(5000);
        Integer actual = sumKeeper.getResult();

        Assertions.assertEquals(expectedSum, actual);
    }

    @Test
    void run_Invalid_stopPrint() throws InnerResourceException {
        HashMap<String, String> resourcesStub = new HashMap<String, String>(){{
            put("abc","1 2 1 1 1 1 1 1 ");
            put("def","2 0 2 5 6 4 57");
            put("ghi","1 2 3 4 5 6 7 8 9 7");
            put("abcdefef","0 1 -1 2 -5 7 -13 24");
            put("abcdefef","0 1 2 1 1 1 1 1 1 1 2 1 1 1 1 1 1 1 2 1 1 1 1 1 1  1- it is invalid");
        }};
        Integer expectedSum = 68;
        LogPrinter logPrinter = getNewLogPrinter();
        ResultSumKeeper sumKeeper = new ResultSumKeeper(logPrinter);

        runApp(resourcesStub, sumKeeper);
        waitThreads(5000);
        try {
            Integer actual = sumKeeper.getResult();
        }
        catch(InnerResourceException ex){
            System.out.println("то что надо");
            return;
        }
        Assertions.fail("При ошибке не должен был вернуться результат!");
    }

    private void runApp(HashMap<String, String> resourcesStub, ResultSumKeeper sumKeeper){
        ApplicationFacade app = getApplicationFacadeByMockResources(resourcesStub, sumKeeper);
        Object[] keyObjects = resourcesStub.keySet().toArray();
        String[] resourceAddresses = Arrays.copyOf(keyObjects, keyObjects.length, String[].class);

        app.Run(resourceAddresses);
    }

    private ApplicationFacade getApplicationFacadeByMockResources(HashMap<String, String> resourcesMock, ResultSumKeeper resultSumKeeper){
        return new ApplicationFacade(resultSumKeeper){
            @Override
            protected ReaderGetterable getReaderGetter() {
                return new ReaderGetterMock(resourcesMock);
            }
        };
    }

    private ResourceStringGetter getResourceStringGetterMock(HashMap<String, String> resourcesMock){
        ReaderGetterDecorator readerGetterDecorator = null;
        return new ResourceStringGetter(readerGetterDecorator){
            @Override
            public String getResourceString(String resourceAddress) throws InnerResourceException {
                return resourcesMock.get(resourceAddress);
            }
        };
    }

    private LogPrinter getNewLogPrinter(){
        return new LogPrinter();
    }

    private void waitThreads(int millis){
        try {
            Thread.currentThread().join(millis); // ожидаем завершения всех потоков
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}