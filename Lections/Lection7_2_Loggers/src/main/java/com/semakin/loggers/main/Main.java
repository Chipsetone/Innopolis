package com.semakin.loggers.main;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author Семакин Виктор
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    static {
        DOMConfigurator.configure("/src/Main/java/com/semakin/loggers/Main/log4j.xml");
    }

    public static void main(String[] args) {
        logger.trace("тестовый лог!");
        System.out.println("завершено");
    }
}
