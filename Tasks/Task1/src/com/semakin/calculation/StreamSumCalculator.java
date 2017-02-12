package com.semakin.calculation;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.resourceGetters.ReaderGetterable;
import com.semakin.threading.IMessagePushable;
import com.semakin.threading.Message;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Chi on 08.02.2017.
 */
public class StreamSumCalculator{
    private ReaderGetterable readerGetter;
    private String resourceAddress;
    private SumAccumulator sumAccumulator;
    private IMessagePushable messagePusher;

    public StreamSumCalculator(ReaderGetterable readerGetter, String resourceAddress, SumAccumulator sumAccumulator, IMessagePushable messagePusher){
        this.readerGetter = readerGetter;
        this.resourceAddress = resourceAddress;
        this.sumAccumulator = sumAccumulator;
        this.messagePusher = messagePusher;
    }

    public void calculateSum() {
        try (Reader reader = readerGetter.getBufferedReader(resourceAddress)) {
            int charCode;
            Character currentChar;

            while ((charCode = reader.read()) != -1) {
                currentChar = ((char) charCode);
                sumAccumulator.processSymbol(currentChar);
            }
            sumAccumulator.completeCalculations();
        } catch (IOException e) {
            pushException(e);
        } catch (InnerResourceException e) {
            pushException(e);
        }
    }

    private void pushException(Exception exception){
        Message message = new Message(exception);
        messagePusher.pushMessage(message);
    }
}
