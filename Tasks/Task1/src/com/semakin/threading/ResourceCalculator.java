package com.semakin.threading;

import com.semakin.calculation.StreamSumCalculator;
import com.semakin.calculation.SumCalculatorFactory;

import java.util.concurrent.Callable;

public class ResourceCalculator implements Callable<Boolean>{
    private String resourceAddress;
    private SumCalculatorFactory sumCalculatorFactory;

    public ResourceCalculator(String resourceAddress, SumCalculatorFactory sumCalculatorFactory) {
        this.resourceAddress = resourceAddress;
        this.sumCalculatorFactory = sumCalculatorFactory;
    }

    @Override
    public Boolean call() {
        StreamSumCalculator sumCalculator = sumCalculatorFactory.getResourceSumCalculator(resourceAddress);
        sumCalculator.calculateSum();
        return true;
    }
}
