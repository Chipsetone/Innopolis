package com.semakin.calculation;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.parsers.StringConverter;
import com.semakin.resourceGetters.ReaderGetterable;
import com.semakin.threading.IMessagePushable;

import java.util.concurrent.Callable;

/**
 * Created by Chi on 08.02.2017.
 */
public class SumCalculatorFactory {
    private ReaderGetterable readerGetter;
    private StringConverter stringConverter;
    private IMessagePushable messagePusher;

    public SumCalculatorFactory(ReaderGetterable readerGetter, StringConverter stringConverter, IMessagePushable messagePusher) {
        this.readerGetter = readerGetter;
        this.stringConverter = stringConverter;
        this.messagePusher = messagePusher;
    }

    public StreamSumCalculator getResourceSumCalculator(String resourceAddress) {
        SumAccumulator sumAccumulator = new SumAccumulator(stringConverter, messagePusher, resourceAddress);
        return new StreamSumCalculator(readerGetter, resourceAddress, sumAccumulator, messagePusher);
    }
}
