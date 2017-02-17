package com.semakin.loggers;

import com.semakin.loggers.emailer.EmailFileSender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.spi.LoggingEvent;

import java.io.File;
import java.io.IOException;

/**
 * Записывает в лог в файл
 * если файл достигает maxFileSize, то отправляет на почту этот файл
 * @author Семакин Виктор
 */
public class FileThenMailAppender extends FileAppender {
    private boolean isNeedSendAndCleanFile = false;
    private long maxFileSizeInBytes;

    @Override
    public void append(LoggingEvent event) {
        super.append(event);

        // если достиг максимального размера, то отправить на почту файл
        // вычистить буфер, закрыть его, и отправить как приложение к письму

        if(isNeedSendAndCleanFile){
            this.reset();
            File file = new File(getFile());
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            isNeedSendAndCleanFile = false;
            // и еще вычистить бы!
            // затем снова открыть
            this.activateOptions();
        }
    }

//    @Override
//    protected boolean shouldFlush(LoggingEvent event) {
//
//        boolean shouldFlushResult = super.shouldFlush(event);
//        if(isReachMaxSize()){
//            System.out.println("файл достиг максимального размера!");
//            isNeedSendAndCleanFile = true;
//            return true;
//        }
//        return shouldFlushResult;
//    }

//    private boolean isReachMaxSize(){
//        File file = new File(getFile());
//        if(file.exists()){
//            long fileSize = file.length();
//            System.out.println("\nfileSize in bytes: " + fileSize);
//            if(fileSize >= getMaxFileSizeInBytes()){
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    protected void reset() {
        super.reset();
    }

//    public long getMaxFileSizeInBytes() {
//        return maxFileSizeInBytes;
//    }
//
//    public void setMaxFileSizeInBytes(long maxFileSizeInBytes) {
//        this.maxFileSizeInBytes = maxFileSizeInBytes;
//    }

}
