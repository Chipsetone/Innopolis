package com.semakin.validation;

/**
 * Валидатор. Проверяет является ли строка числом.
 * @author Виктор Семакин
 */
public class StringAsNumberValidator {
    private final String positiveNumberRegex = "^-?[0-9]{1,}$";

    /**
     * Проверяет является ли строка числом
     * @param value
     * @return true - если переданное значение является числом
     */
    public boolean isNumber(String value){
        return value.matches(positiveNumberRegex);
    }
}
