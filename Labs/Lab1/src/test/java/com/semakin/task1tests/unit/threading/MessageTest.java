package com.semakin.task1tests.unit.threading;

import com.semakin.task1.exceptions.ThreadMessageException;
import com.semakin.task1.threading.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Chi on 11.02.2017.
 */
class MessageTest {

    @Test
    void getMessage_Valid() throws ThreadMessageException {
        int someExpectedValue = 35;
        Message message = new Message(someExpectedValue);

        int actual = message.getMessage();

        Assertions.assertEquals(someExpectedValue, actual);
    }

    @Test
    void getMessage_Invalid() {
        Exception someException = new Exception("something bad happens");

        Message message = new Message(someException);

        try{
            message.getMessage();
        } catch (ThreadMessageException e) {
            System.out.println("исключение - отлично, то что нужно");
            return;
        }

        Assertions.fail("ожидалось исключение!");
    }

    @Test
    void isInvalidMessage_true() {
        Exception innerException = new Exception("somethingBad");
        Message message = new Message(innerException);

        boolean actual = message.isInvalidMessage();

        Assertions.assertTrue(actual);
    }

    @Test
    void isInvalidMessage_false() {
        int someNumber = 333;
        Message message = new Message(someNumber);

        boolean actual = message.isInvalidMessage();

        Assertions.assertFalse(actual);
    }



    @Test
    void getException_ValidMessage_Null() {
        int someNumber = 44;
        Message message = new Message(someNumber);

        Exception actual = message.getException();

        Assertions.assertNull(actual);
    }

    @Test
    void getException_InvalidMessage_NotNull() {
        Exception innerException = new Exception("somethingBad");
        Message message = new Message(innerException);

        Exception actual = message.getException();

        Assertions.assertNotNull(actual);
    }
}