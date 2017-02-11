package tests.unit.threading;

import com.semakin.ResultPrinter;
import com.semakin.threading.MessageProcessor;
import com.semakin.threading.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Chi on 11.02.2017.
 */
class MessageProcessorTest {

    @Test
    void pushMessage_AddingValidMessages() {
        List<String> printerTarget = new LinkedList<>();
        MessageProcessor processor = getMessageProcessor(printerTarget);

        int expectedProcessingMessages = 5;

        pushValidMessages(processor, expectedProcessingMessages);
        processor.runProcessingMessages();
        int actualProcessingMessages = printerTarget.size();

        Assertions.assertEquals(expectedProcessingMessages, actualProcessingMessages);
    }

    @Test
    void pushMessage_AddingInvalidMessage_Stop() {
        List<String> printerTarget = new LinkedList<>();
        MessageProcessor processor = getMessageProcessor(printerTarget);

        int expectedProcessingMessagesCount = 7;
        int unprocessingMessagesCount = 5;
        pushValidMessages(processor, expectedProcessingMessagesCount);
        pushInvalidMessages(processor, unprocessingMessagesCount);

        processor.runProcessingMessages();
        int actualProcessingMessages = printerTarget.size();

        Assertions.assertEquals(expectedProcessingMessagesCount, actualProcessingMessages);
    }

    @Test
    void getLastMessage_AllValidMessages_ContainNumber() {
        List<String> printerTarget = new LinkedList<>();
        MessageProcessor processor = getMessageProcessor(printerTarget);

        int someMessagesCount = 7;
        pushValidMessages(processor, someMessagesCount);

        int expectedLastMessageValue = 100500;
        Message expectedLastMessage = new Message(expectedLastMessageValue);
        processor.pushMessage(expectedLastMessage);

        processor.runProcessingMessages();
        Message actualMessage = processor.getLastMessage();
        int actualMessageValue = actualMessage.getMessage();

        Assertions.assertEquals(expectedLastMessage, actualMessage);
        Assertions.assertEquals(expectedLastMessageValue, actualMessageValue);
    }

    @Test
    void getLastMessage_InvalidMessageInMiddle_FirstInvalidMessage(){
        List<String> printerTarget = new LinkedList<>();
        MessageProcessor processor = getMessageProcessor(printerTarget);

        Exception expectedException = new Exception("smthg bad");
        Message expectedInvalidMessage = new Message(expectedException);
        int expectedProcessedCount = 7;
        int expectedUnprocessedCount = 20;
        pushValidMessages(processor, expectedProcessedCount);
        processor.pushMessage(expectedInvalidMessage);
        pushValidMessages(processor, expectedUnprocessedCount);

        processor.runProcessingMessages();
        Message actualMessage = processor.getLastMessage();
        Exception actualMessageException = actualMessage.getException();

        int actualProcessedCount = printerTarget.size();

        Assertions.assertEquals(expectedInvalidMessage, actualMessage);
        Assertions.assertEquals(expectedException, actualMessageException);
        Assertions.assertEquals(expectedProcessedCount, actualProcessedCount);
    }

    private void pushValidMessages(MessageProcessor messageProcessor, int countOfMessages){
        for (int i = 0; i < countOfMessages; i++) {
            Message validMessage = new Message(i);
            messageProcessor.pushMessage(validMessage);
        }
    }

    private void pushInvalidMessages(MessageProcessor messageProcessor, int countOfMessages){
        for (int i = 0; i < countOfMessages; i++) {
            Exception innerMessageException = new Exception("something Bad: " + i);
            Message validMessage = new Message(innerMessageException);
            messageProcessor.pushMessage(validMessage);
        }
    }

    private MessageProcessor getMessageProcessor(List<String> printerTarget){
        ResultPrinter printer = getFakeLogPrinter(printerTarget);
        return new MessageProcessor(printer);
    }

    private ResultPrinter getFakeLogPrinter(List<String> fakePrinterTarget){
        return new ResultPrinter(){
            @Override
            public void println(String message) {
                fakePrinterTarget.add(message);
            }
        };
    }

}