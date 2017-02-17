package com.semakin.labs.lab1.calculation;

import com.semakin.labs.lab1.resourceGetters.ReaderGetterable;
import com.semakin.labs.lab1.threading.IMessagePushable;
import com.semakin.labs.lab1.threading.Message;
import org.apache.log4j.Logger;

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

    public static final Logger logger = Logger.getLogger(StreamSumCalculator.class);

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
                sumBufferAccumulator.pushToBufferOrRelease(currentChar);
            }
            sumBufferAccumulator.tryReleaseBuffer();
        } catch (Exception e) {
            logger.error("Ошибка при расчете ресурса", e);
            pushException(e);
        }
        logger.debug("Числа из потока считаны и обработаны");
    }
    // TODO NIO2 парсинг файлов использовать оттуда через многопоточный парсинг каждого файла

    private void pushException(Exception exception){
        logger.error("Отправка исключения в очередь расчета");
        Message message = new Message(exception);
        messagePusher.pushMessage(message);
    }
}
