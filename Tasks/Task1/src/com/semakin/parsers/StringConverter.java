package com.semakin.parsers;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.validation.NumberValidatorable;
import com.semakin.validation.ResourceSymbols;
import com.semakin.validation.StringNumberValidator;

import javax.annotation.Resource;

/**
 * Created by Chi on 10.02.2017.
 */
public class StringConverter {
    private StringNumberValidator stringNumberValidator;

    public StringConverter(StringNumberValidator stringNumberValidator){
        this.stringNumberValidator = stringNumberValidator;
    }

    public int toInt(String value) throws InnerResourceException {
        value = value.replace(ResourceSymbols.hyphen, ResourceSymbols.minus);
        value = value.trim();

        if(stringNumberValidator.isNumber(value)){
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
