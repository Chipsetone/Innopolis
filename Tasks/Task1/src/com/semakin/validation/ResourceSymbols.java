package com.semakin.validation;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Chi on 07.02.2017.
 */
public class ResourceSymbols {
    public static final Character minus = '-';
    public static final Character hyphen = '−';
    public static final Character space = ' ';
    public static final String doubleSpace = "  ";

    public static final List<Character> allowedSymbols = Arrays.asList(minus, hyphen, space);
}
