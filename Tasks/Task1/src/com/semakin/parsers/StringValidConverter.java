package com.semakin.parsers;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.validation.NumberValidatorable;
import com.semakin.validation.StringNumberValidator;

/**
 * Created by Chi on 10.02.2017.
 */
public class StringValidConverter extends StringConverter{
    NumberValidatorable numberValidator;
    public StringValidConverter(StringNumberValidator stringValidator, NumberValidatorable numberValidator){
        super(stringValidator);
        this.numberValidator = numberValidator;
    }

    private StringValidConverter(StringNumberValidator stringValidator) {
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
