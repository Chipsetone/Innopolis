package com.semakin.parsers;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.validation.NumberValidatorable;
import com.semakin.validation.StringAsNumberValidator;

/**
 * Created by Chi on 10.02.2017.
 */
public class StringValidConverter extends StringConverter{
    NumberValidatorable numberValidator;
    public StringValidConverter(StringAsNumberValidator stringValidator, NumberValidatorable numberValidator){
        super(stringValidator);
        this.numberValidator = numberValidator;
    }

    private StringValidConverter(StringAsNumberValidator stringValidator) {
        super(stringValidator);
    }

    @Override
    public int toInt(String value) throws InnerResourceException {
        int intResult = super.toInt(value);
        if(numberValidator.isNumberValid(intResult)){
            return  intResult;
        }
        return 0;
    }
}
