package com.semakin.calculation;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.parsers.StringConverter;
import com.semakin.resourceGetters.ReaderGetterable;

import java.io.Reader;

/**
 * Created by Chi on 08.02.2017.
 */
public class StreamSumCalculatorFactory implements SumCalculatorFactory{
    private ReaderGetterable readerGetter;
    private StringConverter stringConverter;

    public StreamSumCalculatorFactory(ReaderGetterable readerGetter, StringConverter stringConverter) {
        this.readerGetter = readerGetter;
        this.stringConverter = stringConverter;
    }

    @Override
    public SumCalculatorable getResourceSumCalculator(String resourceAddress) throws InnerResourceException {
        return new StreamSumCalculator(stringConverter, readerGetter, resourceAddress);
    }
}
