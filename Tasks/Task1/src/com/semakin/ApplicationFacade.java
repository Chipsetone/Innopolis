package com.semakin;

import com.semakin.calculation.StreamSumCalculatorFactory;
import com.semakin.calculation.SumCalculatorFactory;
import com.semakin.calculation.SumCalculatorable;
import com.semakin.exceptions.InnerResourceException;
import com.semakin.parsers.StringConverter;
import com.semakin.parsers.StringValidConverter;
import com.semakin.resourceGetters.ReaderGetterFactory;
import com.semakin.resourceGetters.ReaderGetterable;
import com.semakin.threading.*;
import com.semakin.validation.EvenPositiveNumberValidator;
import com.semakin.validation.NumberValidatorable;
import com.semakin.validation.StringNumberValidator;

/**
 * Created by Chi on 07.02.2017.
 */
public class ApplicationFacade {
    private SumCalculatorFactory sumCalculatorFactory;
    private RunnableService runService;
    private MessageFactory messageFactory;
    private IMessageProcessorable messageProcessor;

//    Конструктор принимающий обьект через который идет печать
    public ApplicationFacade(ResultPrinter resultPrinter) {
        init(resultPrinter);
    }

    private void init(ResultPrinter resultPrinter){
        sumCalculatorFactory = newSumCalculationFactory();
        runService = new RunnableService();

        messageFactory = new MessageFactory();
        messageProcessor = new MessageProcessor(resultPrinter);
    }

    private SumCalculatorFactory newSumCalculationFactory(){
        NumberValidatorable numberValidator = new EvenPositiveNumberValidator();
        StringNumberValidator stringValidator = new StringNumberValidator();
        StringConverter stringConverter = new StringValidConverter(stringValidator, numberValidator);
        ReaderGetterable readerGetter = getReaderGetter();

        return new StreamSumCalculatorFactory(readerGetter, stringConverter);
    }

    protected ReaderGetterable getReaderGetter(){
        ReaderGetterFactory readerGetterFactory = new ReaderGetterFactory();
        return readerGetterFactory.getReaderGetter();
    }

    public void Run(String resourceAddresses[]) {
        for (String resourceAddress: resourceAddresses) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    calculateResource(resourceAddress, messageFactory, messageProcessor);
                }
            };
            runService.runAction(runnable);
        }
        Thread.yield();
        //TODO нужно запустить циклический расчет суммы в очереди.
        // если очередь пуста, то подождать другие потоки
        // если очередь пуста в течение определенного времени, то завершить выполнение
        while(true){
            Message firstMessage = messageProcessor.getLastMessage();
            Thread.yield();
            messageProcessor.runProcessingMessages();
            Message lastMessage = messageProcessor.getLastMessage();

            if(lastMessage != null && (lastMessage.isInvalidMessage() || (firstMessage == lastMessage))){
                System.out.println("Выполнение программы прервано");
                break;
            }
        }
    }
    private void calculateResource(String resourceAddress, MessageFactory messageFactory, IMessagePushable messagePusher){
        try {
            SumCalculatorable sumCalculator = getResourceSumCalculator(resourceAddress);
            Integer resourceSum = sumCalculator.getCalculatedSum();
            Message message = messageFactory.newValidMessage(resourceSum);
            messagePusher.pushMessage(message);
        }
        catch(InnerResourceException ex){
            Message message = messageFactory.newInvalidMessage(ex);
            messagePusher.pushMessage(message);
            System.out.println("вот здесь");
            ex.printStackTrace();
        }
    }

    private SumCalculatorable getResourceSumCalculator(String resourceAddress) throws InnerResourceException {
        return sumCalculatorFactory.getResourceSumCalculator(resourceAddress);
    }
}
