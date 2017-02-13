package com.semakin.calculation;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.resourceGetters.ReaderGetterable;
import com.semakin.threading.IMessagePushable;
import com.semakin.threading.Message;

import java.io.IOException;
import java.io.Reader;

/**
 * Считыватель символов из ресурса
 * @author Виктор Семакин
 */
public class StreamSumCalculator{
    private ReaderGetterable readerGetter;
    private String resourceAddress;
    private SumBufferAccumulator sumBufferAccumulator;
    private IMessagePushable messagePusher;

    /**
     * @param readerGetter получатель доступа к ресурсу
     * @param resourceAddress адрес обрабатываемого ресурса
     * @param sumBufferAccumulator строковый буфер для этого потока
     * @param messagePusher Добавлятор обработанных буфером сообщений в очередь расчета
     */
    public StreamSumCalculator(ReaderGetterable readerGetter, String resourceAddress, SumBufferAccumulator sumBufferAccumulator, IMessagePushable messagePusher){
        this.readerGetter = readerGetter;
        this.resourceAddress = resourceAddress;
        this.sumBufferAccumulator = sumBufferAccumulator;
        this.messagePusher = messagePusher;
    }

    /**
     * Считает символы ресурса и записывает их в sumBufferClculator, а также
     */
    public void calculateSum() {
        try (Reader reader = readerGetter.getBufferedReader(resourceAddress)) {
            int charCode;
            Character currentChar;

            while ((charCode = reader.read()) != -1) {
                currentChar = ((char) charCode);
                sumBufferAccumulator.processSymbol(currentChar);
            }
            sumBufferAccumulator.tryReleaseBuffer();
        } catch (Exception e) {
            pushException(e);
        }
    }
    // TODO NIO2 парсинг файлов использовать оттуда через многопоточный парсинг каждого файла

    private void pushException(Exception exception){
        Message message = new Message(exception);
        messagePusher.pushMessage(message);
    }
}
