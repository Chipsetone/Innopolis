package tests.unit.threading;

import com.semakin.threading.Message;
import com.semakin.threading.MessageFactory;
import org.junit.jupiter.api.Test;

/**
 * Created by Chi on 11.02.2017.
 */
class MessageFactoryTest {
    @Test
    void newValidMessage() {
        MessageFactory factory = getFactory();

        int expectedMessageValue = 25;
        Message message = factory.newValidMessage(expectedMessageValue);
    }

    @Test
    void newInvalidMessage() {

    }

    private MessageFactory getFactory(){
        return new MessageFactory();
    }

}