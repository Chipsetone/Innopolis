package com.semakin.labs.lab1.calculation;

import com.semakin.labs.lab1.parsers.StringConverter;
import com.semakin.labs.lab1.resourceGetters.ReaderGetterable;
import com.semakin.labs.lab1.threading.IMessagePushable;

/**
 * Фабрика расчетчиков ресурсов
 * @author Виктор Семакин
 */
public class SumCalculatorFactory {
    private ReaderGetterable readerGetter;
    private StringConverter stringConverter;
    private IMessagePushable messagePusher;

    /**
     * @param readerGetter получатель доступа к ресурсу
     * @param stringConverter преобразователь слова ресурса в число
     * @param messagePusher заталкиватель подготовленных расчетчиком сообщений в очередь расчета(или вывода. Мы же не должны здесь знать об этом)
     */
    public SumCalculatorFactory(ReaderGetterable readerGetter, StringConverter stringConverter, IMessagePushable messagePusher) {
        this.readerGetter = readerGetter;
        this.stringConverter = stringConverter;
        this.messagePusher = messagePusher;
    }

    public StreamSumCalculator getResourceSumCalculator(String resourceAddress) {
        SumBufferAccumulator sumBufferAccumulator = new SumBufferAccumulator(stringConverter, messagePusher, resourceAddress);
        return new StreamSumCalculator(readerGetter, resourceAddress, sumBufferAccumulator, messagePusher);
    }
}
