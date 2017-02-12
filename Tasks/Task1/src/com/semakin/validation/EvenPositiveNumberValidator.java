package com.semakin.validation;

/**
 * Валидатор целых чисел по условиям задания (целые и пололжительные)
 * @author Виктор Семакин
 */
public class EvenPositiveNumberValidator implements NumberValidatorable{
    /**
     * Проверяет целое число на четность и положительность
     * @param number любое целое число
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
