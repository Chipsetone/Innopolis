package com.semakin.calculation;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.parsers.StringConverter;
import com.semakin.resourceGetters.ReaderGetterable;
import com.semakin.validation.*;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Chi on 08.02.2017.
 */
// допилить и заменить им CollectionSumCalculator
public class StreamSumCalculator implements SumCalculatorable{
    private ReaderGetterable readerGetter;
    private String resourceAddress;
    private SumAccumulator sumAccumulator;

    public StreamSumCalculator(StringConverter stringConverter, ReaderGetterable readerGetter, String resourceAddress){
        this.readerGetter = readerGetter;
        this.resourceAddress = resourceAddress;
        this.sumAccumulator = new SumAccumulator(stringConverter);
    }

    @Override
    public int getCalculatedSum() throws InnerResourceException {
        int resultSum = 0;

        try(Reader reader = readerGetter.getBufferedReader(resourceAddress)){
            int charCode;
            Character currentChar;

            while((charCode=reader.read())!=-1){
                currentChar = ((char)charCode);
                sumAccumulator.processSymbol(currentChar);
            }

            sumAccumulator.completeCalculations();

            return sumAccumulator.getResult();
        } catch (IOException e)  {
            throw new InnerResourceException(e);
        }
    }
}
