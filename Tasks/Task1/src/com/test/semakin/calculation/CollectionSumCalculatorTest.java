package com.test.semakin.calculation;

import com.semakin.calculation.CollectionSumCalculator;
import com.semakin.validation.EvenPositiveNumberValidator;
import com.semakin.validation.NumberValidatorable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chi on 06.02.2017.
 */
class CollectionSumCalculatorTest {
    @Test
    void getCalculatedSum_NotValidElements_ZeroResult() {
        List<Integer> stubCollection = new ArrayList<Integer>();
        stubCollection.add(5);
        stubCollection.add(-1);
        stubCollection.add(35);
        int expectedSum = 0;
        CollectionSumCalculator calculator = getRealSumCalculator(stubCollection);

        int actualSum = calculator.getCalculatedSum();

        Assertions.assertEquals(expectedSum, actualSum);
    }

    @Test
    void getCalculatedSum_EmptyArray_ZeroResult() {
        List<Integer> stubCollection = new ArrayList<Integer>();
        CollectionSumCalculator calculator = getRealSumCalculator(stubCollection);
        int expectedSum = 0;

        int actualSum = calculator.getCalculatedSum();

        Assertions.assertEquals(expectedSum, actualSum);
    }

    @Test
    void getCalculatedSum_ContainValidElements() {
        List<Integer> stubCollection = new ArrayList<Integer>();
        stubCollection.add(3624);
        stubCollection.add(-1);
        stubCollection.add(1488);
        stubCollection.add(3555);
        int expectedSum = 3624 + 1488;
        CollectionSumCalculator calculator = getRealSumCalculator(stubCollection);

        int actualSum = calculator.getCalculatedSum();

        Assertions.assertEquals(expectedSum, actualSum);
    }

    private CollectionSumCalculator getRealSumCalculator(List<Integer> numbersCollection)
    {
        NumberValidatorable realChecker = new EvenPositiveNumberValidator();

        return new CollectionSumCalculator(realChecker, numbersCollection);
    }
}