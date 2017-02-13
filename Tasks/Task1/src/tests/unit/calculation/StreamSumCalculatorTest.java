package tests.unit.calculation;

import com.semakin.calculation.StreamSumCalculator;
import com.semakin.calculation.SumBufferAccumulator;
import com.semakin.exceptions.InnerResourceException;
import com.semakin.parsers.StringConverter;
import com.semakin.parsers.StringValidConverter;
import com.semakin.resourceGetters.ReaderGetterable;
import com.semakin.validation.EvenPositiveNumberValidator;
import com.semakin.validation.NumberValidatorable;
import com.semakin.validation.StringAsNumberValidator;
import tests.mocks.MessagePusherMock;
import tests.mocks.ReaderGetterMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chi on 09.02.2017.
 */
class StreamSumCalculatorTest {
    @Test
    void getCalculatedSum() throws InnerResourceException {
        Map<String, String> resourceStub = new HashMap<>();
        String validNumericResourceAddress = "address";
        resourceStub.put(validNumericResourceAddress, "1 6 24 100 -500 160");
        MessagePusherMock messagePusher = getMessagePusherMock();
        int expected = 6 + 24 + 100 + 160;

        StreamSumCalculator sumCalculator = getStreamSumCalculator(resourceStub, validNumericResourceAddress, messagePusher);

        sumCalculator.calculateSum();
        int actual = messagePusher.getLocalSum();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void calculateSum_InvalidResourceAddress_NoCalculation() {
        Map<String, String> validResourceStub = new HashMap<>();
        String invalidResourceAddress = "badAddress";
        validResourceStub.put("valid Address", "1 5 25 100 -500 160");
        MessagePusherMock messagePusher = getMessagePusherMock();

        StreamSumCalculator sumCalculator = getStreamSumCalculator(validResourceStub, invalidResourceAddress, messagePusher);

        sumCalculator.calculateSum();

        boolean actualStopped = messagePusher.isStopped();

        Assertions.assertTrue(actualStopped);
    }

    @Test
    void getCalculatedSum_InvalidResourceContent_NotStartedNotStopped() {
        String resourceAddress = "someAddress";
        MessagePusherMock messagePusher = getMessagePusherMock();

        StreamSumCalculator sumCalculator = getStreamSumCalculator(null, resourceAddress, messagePusher);

        sumCalculator.calculateSum();

        Assertions.assertFalse(messagePusher.isStarted());
        Assertions.assertTrue(messagePusher.isStopped());
    }

    private StreamSumCalculator getStreamSumCalculator(Map<String, String> resourceStub, String resourceName, MessagePusherMock messagePusher){
        StringAsNumberValidator stringAsNumberValidator = new StringAsNumberValidator();
        NumberValidatorable evenValidator = new EvenPositiveNumberValidator();

        StringConverter stringConverter = new StringValidConverter(stringAsNumberValidator, evenValidator);
        ReaderGetterable readerGetter = new ReaderGetterMock(resourceStub);

        SumBufferAccumulator sumBufferAccumulator = new SumBufferAccumulator(stringConverter, messagePusher, resourceName);
        return new StreamSumCalculator(readerGetter, resourceName, sumBufferAccumulator, messagePusher);
    }

    private MessagePusherMock getMessagePusherMock(){
        return new MessagePusherMock();
    }
}