package com.semakin.parsers;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.validation.ValidSymbols;
import com.semakin.validation.StringAsNumberValidator;

/**
 * Created by Chi on 10.02.2017.
 */
public class StringConverter {
    private StringAsNumberValidator stringAsNumberValidator;

    public StringConverter(StringAsNumberValidator stringAsNumberValidator){
        this.stringAsNumberValidator = stringAsNumberValidator;
    }

    public int toInt(String value) throws InnerResourceException {
        value = value.replace(ValidSymbols.hyphen, ValidSymbols.minus);
        value = value.trim();

        if(stringAsNumberValidator.isNumber(value)){
            try {
                return Integer.parseInt(value);
            }
            catch(NumberFormatException ex){
                throw new InnerResourceException("Не удалось распознать число: " + value, ex);
            }
        }

        throw new InnerResourceException("Не удалось распознать число: " + value);

        // валидация и конвертация strAsNumber
        // замена дефисов на минусы
        // исключение если это не число
    }
}
