package com.semakin.threading;

import com.semakin.calculation.StreamSumCalculator;
import com.semakin.calculation.SumCalculatorFactory;

import java.util.concurrent.Callable;

/**
 * Обработчик ресурса. Требуется для многопоточной обработки
 * @author Виктор Семакин
 */
public class ResourceCalculator implements Callable<Boolean>{
    private String resourceAddress;
    private SumCalculatorFactory sumCalculatorFactory;

    /**
     * @param resourceAddress адрес ресурса, который требуется обработать
     * @param sumCalculatorFactory фабрика расчетчиков ресурсов
     */
    public ResourceCalculator(String resourceAddress, SumCalculatorFactory sumCalculatorFactory) {
        this.resourceAddress = resourceAddress;
        this.sumCalculatorFactory = sumCalculatorFactory;
    }

    /**
     * Обрабатывает ресурс
     * @return true после обработки ресурса
     */
    @Override
    public Boolean call() {
        StreamSumCalculator sumCalculator = sumCalculatorFactory.getResourceSumCalculator(resourceAddress);
        sumCalculator.calculateSum();
        return true;
    }
}
