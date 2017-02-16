package com.semakin.task1;

import org.apache.log4j.Logger;

/**
 * Отображатель результата на экран
 * @author Виктор Семакин
 */
public class ResultPrinter {
    private Logger logger = Logger.getLogger(ResultPrinter.class);
    /**
     * Отображает сообщение на средство вывода
     * @param message
     */
    public void println(String message){
        System.out.println(message);
    }
}
