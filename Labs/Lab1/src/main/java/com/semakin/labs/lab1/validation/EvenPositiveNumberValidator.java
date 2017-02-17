package com.semakin.labs.lab1.validation;

/**
 * {@inheritDoc}
 * (только целые и пололжительные)
 * @author Виктор Семакин
 */
public class EvenPositiveNumberValidator implements NumberValidatorable{
    /**
     * {@inheritDoc}
     * Проверяет целое число на четность и положительность
     * @return true - число четное и положительное
     */
    @Override
    public boolean isNumberValid(int number){
        return number > 0 && isNumberEven(number);
    }

    private boolean isNumberEven(int number){
        return (number % 2 == 0);
    }
}
