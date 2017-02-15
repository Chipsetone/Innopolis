package com.semakin.task1tests.unit.validation;

import com.semakin.task1.validation.StringAsNumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Chi on 07.02.2017.
 */
class StringAsNumberValidatorTest {
    @Test
    void isNumber_Valid_true() {
        StringAsNumberValidator validator = new StringAsNumberValidator();
        String validString = "35";

        boolean actual = validator.isNumber(validString);

        Assertions.assertTrue(actual);
    }

    @Test
    void isNumber_ValidNegative_true() {
        StringAsNumberValidator validator = new StringAsNumberValidator();
        String validNegative = "-100";

        boolean actual = validator.isNumber(validNegative);

        Assertions.assertTrue(actual);
    }

    @Test
    void isNumber_Zero_true() {
        StringAsNumberValidator validator = new StringAsNumberValidator();
        String zero = "0";

        boolean actual = validator.isNumber(zero);

        Assertions.assertTrue(actual);
    }

    @Test
    void isNumber_InvalidString_false() {
        StringAsNumberValidator validator = new StringAsNumberValidator();
        String invalid = "bk^&";

        boolean actual  = validator.isNumber(invalid);

        Assertions.assertFalse(actual);
    }

    @Test
    void isNumber_EmptyString_false() {
        StringAsNumberValidator validator = new StringAsNumberValidator();
        String emptyString = "";

        boolean actual = validator.isNumber(emptyString);

        Assertions.assertFalse(actual);
    }

    @Test
    void isNumber_MinusSuffix_false() {
        StringAsNumberValidator validator = new StringAsNumberValidator();
        String minusSuffix = "45-";

        boolean actual = validator.isNumber(minusSuffix);

        Assertions.assertFalse(actual);
    }

    @Test
    void isNumber_MoreThenOneMinus_false() {
        StringAsNumberValidator validator = new StringAsNumberValidator();
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