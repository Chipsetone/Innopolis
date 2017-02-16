package com.semakin.task1.threading;

import com.semakin.task1.calculation.StreamSumCalculator;
import com.semakin.task1.calculation.SumCalculatorFactory;

import java.util.concurrent.Callable;

/**
 * Обработчик ресурса. Требуется для многопоточной обработки
 * @author Виктор Семакин
 */
public class ResourceCalculator implements Callable<Boolean>, Runnable{
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
     * Обрабатывает ресурс.
     * Реализация интерфейса {@link Callable}
     * @return true после обработки ресурса
     */
    @Override
    public Boolean call() {
        StreamSumCalculator sumCalculator = sumCalculatorFactory.getResourceSumCalculator(resourceAddress);
        sumCalculator.calculateSum();
        return true;
    }

    /**
     * Реализация интерфейса {@link  Runnable}
     * @see ResourceCalculator#call
     */
    @Override
    public void run() {
        call();
    }
}
