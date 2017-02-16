package com.semakin.task1tests.unit.parsers;

import com.semakin.task1.exceptions.InnerResourceException;
import com.semakin.task1.parsers.StringValidConverter;
import com.semakin.task1.validation.EvenPositiveNumberValidator;
import com.semakin.task1.validation.NumberValidatorable;
import com.semakin.task1.validation.StringAsNumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Chi on 10.02.2017.
 */
class StringValidConverterTest {
    @Test
    void toInt_validnumber() throws InnerResourceException {
        StringValidConverter converter = getEvenPositiveConverter();
        String validVictim = "128";
        int expected = 128;

        int actual = converter.toInt(validVictim);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toInt_InvalidNumber_Zero() throws InnerResourceException {
        StringValidConverter converter = getEvenPositiveConverter();
        String invalidVictim = "333";
        int expectedZeroForEvenValidator = 0;

        int actual = converter.toInt(invalidVictim);

        Assertions.assertEquals(expectedZeroForEvenValidator, actual);
    }

    private StringValidConverter getEvenPositiveConverter() {
        StringAsNumberValidator stringValidator = new StringAsNumberValidator();
        NumberValidatorable numberValidator = new EvenPositiveNumberValidator();

        return new StringValidConverter(stringValidator, numberValidator);
    }

}