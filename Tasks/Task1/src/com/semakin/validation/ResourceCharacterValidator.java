package com.semakin.validation;

/**
 * Created by Chi on 07.02.2017.
 */
public class ResourceCharacterValidator {

    public boolean isCharacterValid(Character symbol){
        return ResourceSymbols.allowedSymbols.contains(symbol);
    }
}
