package com.semakin.calculation;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.validation.NumberValidatorable;

import java.util.List;

/**
 * @author Chi
 * @date 06.02.2017
 *
 */
@Deprecated
public class CollectionSumCalculator implements SumCalculatorable {
    private NumberValidatorable numberValidator;
    private List<Integer> numbersCollection;

    public CollectionSumCalculator(NumberValidatorable numberValidator, List<Integer> numbersCollection){
        this.numberValidator = numberValidator;
        this.numbersCollection = numbersCollection;
    }

    public int getCalculatedSum(){
        int resultSum = 0;
        for(int number: numbersCollection){
            if(isNumberValid(number)){
                resultSum += number;
            }
        }
        return resultSum;
    }
    private boolean isNumberValid(int number){
        return numberValidator.isNumberValid(number);
    }
}
