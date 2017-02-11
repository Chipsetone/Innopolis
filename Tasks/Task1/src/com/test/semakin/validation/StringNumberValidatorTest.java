package com.test.semakin.validation;

import com.semakin.validation.StringNumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Chi on 07.02.2017.
 */
class StringNumberValidatorTest {
    @Test
    void isNumber_Valid_true() {
        StringNumberValidator validator = new StringNumberValidator();
        String validString = "35";

        boolean actual = validator.isNumber(validString);

        Assertions.assertTrue(actual);
    }

    @Test
    void isNumber_ValidNegative_true() {
        StringNumberValidator validator = new StringNumberValidator();
        String validNegative = "-100";

        boolean actual = validator.isNumber(validNegative);

        Assertions.assertTrue(actual);
    }

    @Test
    void isNumber_Zero_true() {
        StringNumberValidator validator = new StringNumberValidator();
        String zero = "0";

        boolean actual = validator.isNumber(zero);

        Assertions.assertTrue(actual);
    }

    @Test
    void isNumber_InvalidString_false() {
        StringNumberValidator validator = new StringNumberValidator();
        String invalid = "bk^&";

        boolean actual  = validator.isNumber(invalid);

        Assertions.assertFalse(actual);
    }

    @Test
    void isNumber_EmptyString_false() {
        StringNumberValidator validator = new StringNumberValidator();
        String emptyString = "";

        boolean actual = validator.isNumber(emptyString);

        Assertions.assertFalse(actual);
    }

    @Test
    void isNumber_MinusSuffix_false() {
        StringNumberValidator validator = new StringNumberValidator();
        String minusSuffix = "45-";

        boolean actual = validator.isNumber(minusSuffix);

        Assertions.assertFalse(actual);
    }

    @Test
    void isNumber_MoreThenOneMinus_false() {
        StringNumberValidator validator = new StringNumberValidator();
        String doubleMinusAtStart = "--5";
        String doubleMinusAtEnd = "45--";
        String doubleMinusOnly = "--";
        String moreMinuses = "--------98-3";

        boolean actualMinusesAtStart = validator.isNumber(doubleMinusAtStart);
        boolean actualMinusesAtEnd = validator.isNumber(doubleMinusAtEnd);
        boolean actualMinusOnly = validator.isNumber(doubleMinusOnly);
        boolean actualMoreMinuses = validator.isNumber(moreMinuses);

        Assertions.assertFalse(actualMinusesAtEnd);
        Assertions.assertFalse(actualMinusesAtStart);
        Assertions.assertFalse(actualMinusOnly);
        Assertions.assertFalse(actualMoreMinuses);
    }
}