package com.semakin.loggers;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author Семакин Виктор
 */

public class EmailLayout extends PatternLayout{

    @Override
    public String format(LoggingEvent event) {
        LoggerMessage message = (LoggerMessage) event.getMessage();

        return message.getMessage();
    }
}
