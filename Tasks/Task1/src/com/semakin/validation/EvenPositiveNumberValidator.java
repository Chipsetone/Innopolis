package com.semakin.validation;

/**
 * Created by Chi on 06.02.2017.
 */
public class EvenPositiveNumberValidator implements NumberValidatorable{
    /**
     * Проверяет целое число на четность и положительность
     * @param number
     * @return
     */
    @Override
    public boolean isNumberValid(int number){
        return number > 0 && isNumberEven(number);
    }

    private boolean isNumberEven(int number){
        return (number % 2 == 0);
    }
}
