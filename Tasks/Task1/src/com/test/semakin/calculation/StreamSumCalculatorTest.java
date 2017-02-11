package com.test.semakin.calculation;

import com.semakin.calculation.StreamSumCalculator;
import com.semakin.exceptions.InnerResourceException;
import com.semakin.parsers.StringConverter;
import com.semakin.parsers.StringValidConverter;
import com.semakin.resourceGetters.ReaderGetterable;
import com.semakin.validation.EvenPositiveNumberValidator;
import com.semakin.validation.NumberValidatorable;
import com.semakin.validation.StringNumberValidator;
import com.test.semakin.mocks.ReaderGetterMock;
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
        int expected = 6 + 24 + 100 + 160;

        StreamSumCalculator sumCalculator = getStreamSumCalculator(resourceStub, validNumericResourceAddress);

        int actual = sumCalculator.getCalculatedSum();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getCalculatedSum_InvalidResourceAddress_Exception() {
        Map<String, String> validResourceStub = new HashMap<>();
        String invalidResourceAddress = "badAddress";
        validResourceStub.put("valid Address", "1 5 25 100 -500 160");

        StreamSumCalculator sumCalculator = getStreamSumCalculator(validResourceStub, invalidResourceAddress);

        try {
            int actual = sumCalculator.getCalculatedSum();
        } catch (InnerResourceException e) {
            System.out.println("ok");
            return;
        }

        Assertions.fail("всё плохо... всё очень и очень плохо.");
    }

    @Test
    void getCalculatedSum_InvalidResourceContent_Exception() {
        Map<String, String> invalidResource = null;
        String resourceAddress = "someAddress";

        StreamSumCalculator sumCalculator = getStreamSumCalculator(invalidResource, resourceAddress);

        try {
            int actual = sumCalculator.getCalculatedSum();
        } catch (InnerResourceException e) {
            System.out.println("ok");
            return;
        }

        Assertions.fail("всё плохо... всё очень и очень плохо.");
    }

    private StreamSumCalculator getStreamSumCalculator(Map<String, String> resourceStub, String resourceName){
        StringNumberValidator stringNumberValidator = new StringNumberValidator();
        NumberValidatorable evenValidator = new EvenPositiveNumberValidator();

        StringConverter stringConverter = new StringValidConverter(stringNumberValidator, evenValidator);
        ReaderGetterable readerGetter = new ReaderGetterMock(resourceStub);

        return new StreamSumCalculator(stringConverter, readerGetter, resourceName);
    }
}