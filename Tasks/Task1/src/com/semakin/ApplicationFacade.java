package com.semakin;

import com.semakin.calculation.SumCalculatorFactory;
import com.semakin.parsers.StringConverter;
import com.semakin.parsers.StringValidConverter;
import com.semakin.resourceGetters.ReaderGetterFactory;
import com.semakin.resourceGetters.ReaderGetterable;
import com.semakin.threading.*;
import com.semakin.validation.EvenPositiveNumberValidator;
import com.semakin.validation.NumberValidatorable;
import com.semakin.validation.StringNumberValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Chi on 07.02.2017.
 */
public class ApplicationFacade {
    private SumCalculatorFactory sumCalculatorFactory;
    private RunnableService runService;
    private IMessageProcessorable messageProcessor;
    private AtomicBoolean isComplete = new AtomicBoolean(false);
    private final ResultPrinter resultPrinter;

    public ApplicationFacade(ResultPrinter resultPrinter) {
        this.resultPrinter = resultPrinter;
        init(resultPrinter);
    }

    public void Run(String resourceAddresses[]) {
        List<ResourceCalculator> resourceCalculators = new ArrayList<ResourceCalculator>();
        for (String resourceAddress : resourceAddresses) {
            ResourceCalculator resourceCalculator = getNewResourceCalculator(resourceAddress);
            resourceCalculators.add(resourceCalculator);
        }

        Runnable run = new Runnable() {
            @Override
            public void run() {
                while (!isComplete.get()) {
                    messageProcessor.runProcessingMessages();
                    Message lastMessage = messageProcessor.getLastMessage();
                    Thread.yield();

                    if ((lastMessage != null) && lastMessage.isInvalidMessage()) {
                        int result = messageProcessor.getSum();
                        System.out.println("Выполнение программы прервано.");
                        break;
                    }
                }
                int result = messageProcessor.getSum();
                System.out.println("Завершено. Результат " + result);
            }
        };
        runService.addAction(run);

        calculateResources(resourceCalculators);
    }

    private void init(ResultPrinter resultPrinter) {
        runService = new RunnableService();
        messageProcessor = new MessageProcessor(resultPrinter);
        sumCalculatorFactory = newSumCalculationFactory();
    }

    private SumCalculatorFactory newSumCalculationFactory() {
        NumberValidatorable numberValidator = new EvenPositiveNumberValidator();
        StringNumberValidator stringValidator = new StringNumberValidator();
        StringConverter stringConverter = new StringValidConverter(stringValidator, numberValidator);
        ReaderGetterable readerGetter = getReaderGetter();

        return new SumCalculatorFactory(readerGetter, stringConverter, messageProcessor);
    }

    protected ReaderGetterable getReaderGetter() {
        ReaderGetterFactory readerGetterFactory = new ReaderGetterFactory();
        return readerGetterFactory.getReaderGetter();
    }


    private void calculateResources(List<ResourceCalculator> resourceCalculators){
        try {
            runService.invokeAll(resourceCalculators);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isComplete.set(true);

    }

    private ResourceCalculator getNewResourceCalculator(String resourceAddress){
        return new ResourceCalculator(resourceAddress, sumCalculatorFactory);
    }
}
