package com.semakin.parsers;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.validation.ResourceSymbols;
import com.semakin.validation.StringNumberValidator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Chi on 07.02.2017.
 */
@Deprecated
public class CollectionParser {
    private final Character hyphenSign = ResourceSymbols.hyphen;
    private final Character minusSign = ResourceSymbols.minus;
    private final Character space = ResourceSymbols.space;
    private final List<Character> allowedCharacters = ResourceSymbols.allowedSymbols;

    private StringNumberValidator numberValidator;

    public CollectionParser(StringNumberValidator numberValidator){
        this.numberValidator = numberValidator;
    }

    public List<Integer> parseStringContent(final String resourceContent) throws InnerResourceException {
        if(resourceContent == null){
            throw new InnerResourceException("Ошибка преобразования содержимого ресурса. Принятая строка содержит null");
        }

        String cleanString = getCleanString(resourceContent);
        if (cleanString.length() == 0) {
            throw new InnerResourceException("Ошибка преобразования ресурса. Принятая строка пуста.");
        }
        List<String> stringList = getCollectionFromString(cleanString);

        return convertToIntegerList(stringList);
    }

    private List<Integer> convertToIntegerList(final List<String> stringList) throws InnerResourceException{
        List<Integer> result = new LinkedList<Integer>();
        for (String item: stringList) {
            Integer value = getValueFromString(item);
            result.add(value);
        }

        return result;
    }

    private Integer getValueFromString(final String stringValue) throws InnerResourceException{
        if(isNumber(stringValue)){
            return convertFromString(stringValue);
        }
        else{
            convertFromString(stringValue); // тут мы узнаем тип исключения. Надо добить эту ситуацию
            throw new UnsupportedOperationException();
        }
    }

    private String getCleanString(String value){
        String resultString = getWithoutDoubleSpaces(value);
        resultString = resultString.replace(hyphenSign, minusSign);

        return resultString.trim();
    }

    private String getWithoutDoubleSpaces(String value){
        final String doubleSpace = "  ";
        final String singleSpace = " ";

        String resultString = value.replace(doubleSpace, singleSpace);

        while(resultString.indexOf(doubleSpace) > 0){
            resultString = resultString.replace(doubleSpace, singleSpace);
        }

        return resultString;
    }

    private List<String> getCollectionFromString(String value){
        final String delimiter = String.valueOf(space);
        String[] charArray = value.split(delimiter);
        return Arrays.asList(charArray);
    }

    private boolean isNumber(String value){
        return numberValidator.isNumber(value);
    }

    private Integer convertFromString(final String value) throws InnerResourceException{
        try {
            return Integer.parseInt(value);
        }
        catch(NumberFormatException ex){
            throw new InnerResourceException("Ошибка конвертации числа " + value);
        }
    }
}
