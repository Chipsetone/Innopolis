package com.semakin.loggers;

import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * @author Семакин Виктор
 */

public class EmailRollingFileAppender extends RollingFileAppender {
    @Override
    public void rollOver() {

        long size = ((CountingQuietWriter) qw).getCount();
        System.out.println("\ncalled rollOver size = " + size + " maxSize " + maxFileSize);
//        super.rollOver();
        File target;
        File file;
        file = new File(fileName);
        file.delete();
        // отправить файл
        // затем очистить
        // затем заново его установить
        try {
            this.setFile(fileName, true, bufferedIO, bufferSize);
        }
        catch(IOException e) {
            if (e instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("setFile("+fileName+", true) call failed.", e);
        }
        return;
//
////        if (qw != null) {
////            long size = ((CountingQuietWriter) qw).getCount();
////            LogLog.debug("rolling over count=" + size);
////            //   if operation fails, do not roll again until
////            //      maxFileSize more bytes are written
////            nextRollover = size + maxFileSize;
////        }
////        LogLog.debug("maxBackupIndex="+maxBackupIndex);
//
//        boolean renameSucceeded = true;
//        // If maxBackups <= 0, then there is no file renaming to be done.
//        if(maxBackupIndex > 0) {
//            // Delete the oldest file, to keep Windows happy.
//            file = new File(fileName + '.' + maxBackupIndex);
//            if (file.exists())
//                renameSucceeded = file.delete();
//
//            // Map {(maxBackupIndex - 1), ..., 2, 1} to {maxBackupIndex, ..., 3, 2}
//            for (int i = maxBackupIndex - 1; i >= 1 && renameSucceeded; i--) {
//                file = new File(fileName + "." + i);
//                if (file.exists()) {
//                    target = new File(fileName + '.' + (i + 1));
//                    LogLog.debug("Renaming file " + file + " to " + target);
//                    renameSucceeded = file.renameTo(target);
//                }
//            }
//
//            if(renameSucceeded) {
//                // Rename fileName to fileName.1
//                target = new File(fileName + "." + 1);
//
//                this.closeFile(); // keep windows happy.
//
//                file = new File(fileName);
//                LogLog.debug("Renaming file " + file + " to " + target);
//                renameSucceeded = file.renameTo(target);
//                //
//                //   if file rename failed, reopen file with append = true
//                //
//                if (!renameSucceeded) {
//                    try {
//                        this.setFile(fileName, true, bufferedIO, bufferSize);
//                    }
//                    catch(IOException e) {
//                        if (e instanceof InterruptedIOException) {
//                            Thread.currentThread().interrupt();
//                        }
//                        LogLog.error("setFile("+fileName+", true) call failed.", e);
//                    }
//                }
//            }
//        }
//
//        //
//        //   if all renames were successful, then
//        //
//        if (renameSucceeded) {
//            try {
//                // This will also close the file. This is OK since multiple
//                // close operations are safe.
//                this.setFile(fileName, false, bufferedIO, bufferSize);
//                //nextRollover = 0;
//            }
//            catch(IOException e) {
//                if (e instanceof InterruptedIOException) {
//                    Thread.currentThread().interrupt();
//                }
//                LogLog.error("setFile("+fileName+", false) call failed.", e);
//            }
//        }

    }
}
