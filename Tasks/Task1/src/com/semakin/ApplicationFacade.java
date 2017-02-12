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
 * Фасад приложения для обработки ресурсов,
 * скрывает в себе всю инициализацию/конфигурацию
 */
public class ApplicationFacade {
    private SumCalculatorFactory sumCalculatorFactory;
    private RunnableService runService;
    private MessageProcessor messageProcessor;
    private AtomicBoolean isComplete = new AtomicBoolean(false);
    private final ResultPrinter resultKeeper;

    /**
     * Инициализатор приложения для обработки
     * @param resultKeeper
     */
    public ApplicationFacade(ResultPrinter resultKeeper) {
        this.resultKeeper = resultKeeper;
        init(resultKeeper);
    }

    /**
     * Обработка ресурсов по их адресу в мнгогопоточном режиме
     * @param resourceAddresses адреса ресурсов
     */
    public void Run(String resourceAddresses[]) {
        List<ResourceCalculator> resourceCalculators = getResourceCalculators(resourceAddresses);
        calculateResources(resourceCalculators);
        while (true){
            messageProcessor.runProcessingMessages();

            if (isComplete.get() && messageProcessor.isStopped()){
                break;
            }
        }
        int result = messageProcessor.getSum();
        System.out.println("Завершено. Результат " + result);
    }

    /**
     * Возвращает получатель потока из ресурса
     * Переопределяется в целях тестирования
     * @return возвращатель читателя потока (Stream) на основании адреса
     */
    protected ReaderGetterable getReaderGetter() {
        ReaderGetterFactory readerGetterFactory = new ReaderGetterFactory();
        return readerGetterFactory.getReaderGetter();
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


    private void calculateResources(List<ResourceCalculator> resourceCalculators){
        try {
            runService.invokeAll(resourceCalculators);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isComplete.set(true);
    }

    private List<ResourceCalculator> getResourceCalculators(String resourceAddresses[]) {
        List<ResourceCalculator> resourceCalculators = new ArrayList<ResourceCalculator>();

        for (String resourceAddress : resourceAddresses) {

            resourceAddress = resourceAddress.trim();
            if(resourceAddress.length() == 0){
                continue;
            }

            ResourceCalculator resourceCalculator = new ResourceCalculator(resourceAddress, sumCalculatorFactory);
            resourceCalculators.add(resourceCalculator);
        }

        return resourceCalculators;
    }
}
