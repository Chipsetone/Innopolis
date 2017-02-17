package com.semakin.loggers.main;

import com.semakin.loggers.LoggerMessage;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author Семакин Виктор
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    static {
        DOMConfigurator.configure("D:\\git\\Innopolis\\Lections\\Lection7_2_Loggers\\src\\main\\resources\\log4j.xml");
    }

    public static void main(String[] args) {
        advancedEmailLogger();

        System.out.println("завершено");
    }

    private static void simpleEmailLogger(){
        LoggerMessage message = new LoggerMessage("тестовый лог!");
        logger.trace(message);
    }

    private static void advancedEmailLogger(){
        for (int i = 0; i < 1000; i++) {
            LoggerMessage message = new LoggerMessage("тестовый лог " + i);
            logger.trace(message);
        }
    }
}
