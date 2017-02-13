package com.semakin.validation;

import java.util.Arrays;
import java.util.List;

/**
 * Класс-Хелпер
 * содержит константы допустимых символов,
 * которые могут содержатся в ресурсе
 * @author Виктор Семакин
 */
public final class ValidSymbols {
    public static final Character minus = '-';
    public static final Character hyphen = '−';
    public static final Character space = ' ';

    public static final List<Character> allowedSymbols = Arrays.asList(minus, hyphen, space);
}
