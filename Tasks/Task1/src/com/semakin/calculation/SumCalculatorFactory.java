package com.semakin.calculation;

import com.semakin.exceptions.InnerResourceException;

/**
 * Created by Chi on 08.02.2017.
 */
public interface SumCalculatorFactory {
    public SumCalculatorable getResourceSumCalculator(String resourceAddress) throws InnerResourceException;
}
