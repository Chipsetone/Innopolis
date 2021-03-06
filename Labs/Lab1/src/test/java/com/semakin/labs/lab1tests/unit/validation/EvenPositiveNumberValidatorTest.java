package com.semakin.labs.lab1tests.unit.validation;

import com.semakin.labs.lab1.validation.EvenPositiveNumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Семакин Виктор
 */
class EvenPositiveNumberValidatorTest {
    @Test
    void isNumberValid_positiveEven_true() {
        EvenPositiveNumberValidator checker = new EvenPositiveNumberValidator();
        int positiveEven = 4;
        
        boolean actual = checker.isNumberValid(positiveEven);

        Assertions.assertTrue(actual);
    }

    @Test
    void isNumberValid_positiveNotEven_false() {
        EvenPositiveNumberValidator checker = new EvenPositiveNumberValidator();
        int positiveNotEven = 3;
        
        boolean actual = checker.isNumberValid(positiveNotEven);
        
        Assertions.assertFalse(actual);
    }

    @Test
    void isNumberValid_negativeEven_false() {
        EvenPositiveNumberValidator checker = new EvenPositiveNumberValidator();
        int negativeEven = -22;
        
        boolean actual = checker.isNumberValid(negativeEven);
        
        Assertions.assertFalse(actual);
    }

    @Test
    void isNumberValid_negativeNotEven_false() {
        EvenPositiveNumberValidator checker = new EvenPositiveNumberValidator();
        int negativeNotEven = -39;

        boolean actual = checker.isNumberValid(negativeNotEven);

        Assertions.assertFalse(actual);
    }

    @Test
    void isNumberValid_zero_false() {
        EvenPositiveNumberValidator checker = new EvenPositiveNumberValidator();
        int zero = 0;

        boolean actual = checker.isNumberValid(zero);

        Assertions.assertFalse(actual);
    }
}