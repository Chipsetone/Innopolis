package com.semakin.validation;

/**
 * Валидатор целых чисел по условиям задания
 * @author Виктор Семакин
 */
public interface NumberValidatorable {
    /**
     * Проверяет число
     * @param number число
     * @return true - соответствует условиям
     */
    boolean isNumberValid(int number);
}
