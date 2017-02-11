package com.semakin.validation;

/**
 *
 */
public class StringNumberValidator {
    private final String positiveNumberRegex = "^[0-9]{1,}$";

    public boolean isNumber(String value){
        if(value.indexOf('-') == 0){
            value = value.substring(1);
        }

        return value.matches(positiveNumberRegex);
    }
}
