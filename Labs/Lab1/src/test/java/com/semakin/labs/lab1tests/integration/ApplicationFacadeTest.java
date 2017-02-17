package com.semakin.labs.lab1tests.integration;

import com.semakin.labs.lab1.ApplicationFacade;
import com.semakin.labs.lab1.ResultPrinter;
import com.semakin.labs.lab1.exceptions.InnerResourceException;
import com.semakin.labs.lab1.resourceGetters.ReaderGetterable;
import com.semakin.labs.lab1tests.mocks.ResultPrinterMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.semakin.labs.lab1tests.mocks.ReaderGetterMock;

import java.util.Arrays;
import java.util.HashMap;

/**
 * тесты приложения.
 * В качестве входых и выходных данных используются заглушки
 * ReaderGetterMock и ResultPrinterMock
 */
class ApplicationFacadeTest {
    @Test
    void run_Valid_RepeatableResult() throws InnerResourceException {
        HashMap<String, String> resourcesStub = new HashMap<String, String>(){{
            put("abc","1 1 1 1 1 1 1 1 ");
            put("def","2 0 2 5 6 4 57");
            put("ghi","1 2 3 4 5 6 7 8 9 7");
            put("abcdefef","0 1 -1 2 -5 7 -13 24");
            put("87gu","1 2 3 4");
            put("qwert","2");
            put("кирилица","1");
        }};
        int expectedSum = 68;
        ResultPrinterMock resultPrinter = getResultPrinterMock();

        runApp(resourcesStub, resultPrinter);

        String actualLastMessage = resultPrinter.getLastMessage();
        System.out.println("последнее сообщение " + actualLastMessage);
        int actualSum = Integer.parseInt(actualLastMessage);

        Assertions.assertEquals(expectedSum, actualSum);
    }

    @Test
    void run_Invalid_stopPrint() throws InnerResourceException {
        HashMap<String, String> resourcesStub = new HashMap<String, String>(){{
            put("abc","1 2 1 1 1 1 1 1 ");
            put("def","2 0 2 5 6 4 57");
            put("abcdefef","0 1 2 1 1 1 1 1 1 1 2 1 1 1 1 1 1 1 2 1 1 1 1 1 1  1- it is invalid");
            put("ghi","1 2 3 4 5 6 7 8 9 7");
            put("abcdefef","0 1 -1 2 -5 7 -13 24");
        }};
        int unExpectedSum = 68;
        ResultPrinterMock resultPrinter = getResultPrinterMock();

        runApp(resourcesStub, resultPrinter);
        String lastMessage = resultPrinter.getLastMessage();
        System.out.println("последнее сообщение: " + lastMessage);
        int actualLastSum = Integer.parseInt(lastMessage);

        Assertions.assertNotEquals(unExpectedSum, actualLastSum);
    }

    // TODO проверить остановку всех потоков при ошибочных данных в одном из потоков

    private void runApp(HashMap<String, String> resourcesStub, ResultPrinter resultPrinter){
        ApplicationFacade app = getApplicationFacadeByMockResources(resourcesStub, resultPrinter);
        Object[] keyObjects = resourcesStub.keySet().toArray();
        String[] resourceAddresses = Arrays.copyOf(keyObjects, keyObjects.length, String[].class);

        app.Run(resourceAddresses);
    }

    private ApplicationFacade getApplicationFacadeByMockResources(HashMap<String, String> resourcesMock, ResultPrinter resultPrinter){
        return new ApplicationFacade(resultPrinter){
            @Override
            protected ReaderGetterable getReaderGetter() {
                return new ReaderGetterMock(resourcesMock);
            }
        };
    }

    private ResultPrinterMock getResultPrinterMock(){
        return new ResultPrinterMock();
    }

    private void waitThreads(int millis){
        try {
            Thread.currentThread().join(millis); // ожидаем завершения всех потоков
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}