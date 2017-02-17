package com.semakin.labs.lab1tests.unit.parsers;

import com.semakin.labs.lab1.exceptions.InnerResourceException;
import com.semakin.labs.lab1.parsers.StringConverter;
import com.semakin.labs.lab1.validation.ValidSymbols;
import com.semakin.labs.lab1.validation.StringAsNumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Chi on 10.02.2017.
 */
class StringConverterTest {
    @Test
    void toInt_validNumbers() throws InnerResourceException {
        StringConverter converter = getStringConverter();

        String converterVictimPositive = "123";
        int expectedPositive = 123;
        String converterVictimNegative = "-456";
        int expectedNegative = -456;
        String zero = "0";
        int expectedZero = 0;


        int actualPositive = converter.toInt(converterVictimPositive);
        int actualNegative = converter.toInt(converterVictimNegative);
        int actualZero = converter.toInt(zero);

        Assertions.assertEquals(expectedPositive, actualPositive);
        Assertions.assertEquals(expectedNegative, actualNegative);
        Assertions.assertEquals(expectedZero, actualZero);
    }

    @Test
    void toInt_OnlySpaces_exception() {
        StringConverter converter = getStringConverter();

        String spaces = "   ";
        try {
            converter.toInt(spaces);
        }
        catch (InnerResourceException e) {
            System.out.println("сойдет");
            return;
        }

        Assertions.fail("ожидалось исключение");
    }

    @Test
    void toInt_Empty_exception() {
        StringConverter converter = getStringConverter();

        String empty = "";
        try {
            converter.toInt(empty);
        }
        catch (InnerResourceException e) {
            System.out.println("сойдет");
            return;
        }

        Assertions.fail("ожидалось исключение");
    }

    @Test
    void toInt_ValidNumberWithHyphen_Valid() throws InnerResourceException {
        StringConverter converter = getStringConverter();
        String hyphenable = ValidSymbols.hyphen + "123";
        int expected = -123;

        int actual = converter.toInt(hyphenable);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toInt_MinusSuffix_Exception(){
        StringConverter converter = getStringConverter();

        String minusSuffix = "345-";
        try {
            converter.toInt(minusSuffix);
        }
        catch (InnerResourceException e) {
            System.out.println("сойдет");
            return;
        }

        Assertions.fail("ожидалось исключение");
    }

    @Test
    void toInt_doubleMinus_Exception() {
        StringConverter converter = getStringConverter();

        String doubleMinus = "--7823";
        try {
            converter.toInt(doubleMinus);
        }
        catch (InnerResourceException e) {
            System.out.println("сойдет");
            return;
        }

        Assertions.fail("ожидалось исключение");
    }

    private StringConverter getStringConverter() {
        StringAsNumberValidator stringValidator = new StringAsNumberValidator();
        return new StringConverter(stringValidator);
    }
}