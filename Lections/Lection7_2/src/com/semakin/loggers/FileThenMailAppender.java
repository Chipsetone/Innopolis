package com.semakin.loggers;

import org.apache.log4j.FileAppender;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Записывает в лог в файл
 * если файл достигает maxFileSize, то отправляет на почту этот файл
 * @author Семакин Виктор
 */
public class FileThenMailAppender extends FileAppender {
    private long maxFileSizeInBytes;

    @Override
    public void append(LoggingEvent event) {
        super.append(event);

        // если достиг максимального размера, то отправить на почту файл
        // вычистить буфер, закрыть его, и отправить как приложение к письму
        if(isReachMaxSize()){
            this.reset();
            // затем снова открыть
            this.activateOptions();
        }
    }

    @Override
    protected boolean shouldFlush(LoggingEvent event) {
        if(isReachMaxSize()){
            return true;
        }
        return super.shouldFlush(event);
    }

    private boolean isReachMaxSize(){
        throw new UnsupportedOperationException();
    }

    @Override
    protected void reset() {
        super.reset();
    }
}
