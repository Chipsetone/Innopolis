package com.semakin.calculation;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.parsers.CollectionParser;
import com.semakin.resourceGetters.ResourceStringGetter;
import com.semakin.validation.NumberValidatorable;

import java.util.List;

/**
 * Created by Chi on 08.02.2017.
 */
@Deprecated
public class CollectionSumCalculatorFactory implements SumCalculatorFactory{
    private ResourceStringGetter resourceStringGetter;
    private CollectionParser collectionParser;
    private NumberValidatorable numberValidator;

    public CollectionSumCalculatorFactory(ResourceStringGetter resourceStringGetter, CollectionParser collectionParser, NumberValidatorable numberValidator) {
        this.resourceStringGetter = resourceStringGetter;
        this.collectionParser = collectionParser;
        this.numberValidator = numberValidator;
    }

    public SumCalculatorable getResourceSumCalculator(String resourceAddress) throws InnerResourceException {
        String resourceContent = getResourceString(resourceAddress);
        List<Integer> numbers = parseStringToCollection(resourceContent);

        return getNewSumCalculator(numbers);
    }

    private String getResourceString(String resourceAddress) throws InnerResourceException {
        return resourceStringGetter.getResourceString(resourceAddress);
    }

    private List<Integer> parseStringToCollection(String resourceContent) throws InnerResourceException {
        return collectionParser.parseStringContent(resourceContent);
    }

    private SumCalculatorable getNewSumCalculator(List<Integer> numbers){
        return new CollectionSumCalculator(numberValidator, numbers);
    }
}
