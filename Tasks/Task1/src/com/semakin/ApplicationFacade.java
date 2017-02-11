package com.semakin;

import com.semakin.calculation.StreamSumCalculatorFactory;
import com.semakin.calculation.SumCalculatorFactory;
import com.semakin.calculation.SumCalculatorable;
import com.semakin.exceptions.InnerResourceException;
import com.semakin.parsers.StringConverter;
import com.semakin.parsers.StringValidConverter;
import com.semakin.resourceGetters.ReaderGetterFactory;
import com.semakin.resourceGetters.ReaderGetterable;
import com.semakin.threading.RunnableService;
import com.semakin.validation.EvenPositiveNumberValidator;
import com.semakin.validation.NumberValidatorable;
import com.semakin.validation.StringNumberValidator;

/**
 * Created by Chi on 07.02.2017.
 */
public class ApplicationFacade {
    private SumCalculatorFactory sumCalculatorFactory;
    private RunnableService runService;
    private ResultSumKeeper resultSumKeeper;

    public ApplicationFacade(ResultSumKeeper resultSumKeeper) {
        this.resultSumKeeper = resultSumKeeper;
        initSumCalculationFactory();
        init();
    }

    private void init(){
        runService = new RunnableService();
        LogPrinter logPrinter = new LogPrinter();
    }

    private void initSumCalculationFactory(){
        NumberValidatorable numberValidator = new EvenPositiveNumberValidator();
        StringNumberValidator stringValidator = new StringNumberValidator();
        StringConverter stringConverter = new StringValidConverter(stringValidator, numberValidator);
        ReaderGetterable readerGetter = getReaderGetter();

        sumCalculatorFactory = new StreamSumCalculatorFactory(readerGetter, stringConverter);
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
                    try {
                        calculateResource(resourceAddress, resultSumKeeper);
                    } catch (InnerResourceException e) {
                        e.printStackTrace();
                    }
                }
            };
            //calculateResource(resourceAddress, resultSumKeeper);
            runService.runAction(runnable);
        }
//        catch(InnerResourceException ex){
//            System.out.println("Произошла ошибка. (" + ex.getMessage() + ")");
//        }
    }
    private void calculateResource(String resourceAddress, ResultSumKeeper resultSumKeeper) throws InnerResourceException {
        SumCalculatorable sumCalculator = getResourceSumCalculator(resourceAddress);
        Integer resourceSum = sumCalculator.getCalculatedSum();

        resultSumKeeper.addResult(resourceSum);
    }

    private SumCalculatorable getResourceSumCalculator(String resourceAddress) throws InnerResourceException {
        return sumCalculatorFactory.getResourceSumCalculator(resourceAddress);
    }
}
