package com.semakin.parsers;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.validation.NumberValidatorable;
import com.semakin.validation.StringAsNumberValidator;

/**
 * {@inheritDoc}
 * Преобразователь числа с пост-валидацией
 * @author Виктор Семакин
 */
public class StringValidConverter extends StringConverter{
    NumberValidatorable numberValidator;

    /**
     * @param stringValidator проверятор еще непреобразованной строки
     * @param numberValidator проверятор преобразованного числа
     */
    public StringValidConverter(StringAsNumberValidator stringValidator, NumberValidatorable numberValidator){
        super(stringValidator);
        this.numberValidator = numberValidator;
    }

    private StringValidConverter(StringAsNumberValidator stringValidator) {
        super(stringValidator);
    }

    /**
     * {@inheritDoc}
     * Также после преобразования проверяет число с помощью NumberValidatorable
     */
    @Override
    public int toInt(String value) throws InnerResourceException {
        int intResult = super.toInt(value);
        if(numberValidator.isNumberValid(intResult)){
            return  intResult;
        }
        return 0;
    }
}
