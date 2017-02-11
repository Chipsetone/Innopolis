package com.semakin.resourceGetters;

import com.semakin.exceptions.InnerResourceException;

import java.io.BufferedReader;
import java.io.Reader;

/**
 * Created by Chi on 08.02.2017.
 */
public abstract class ReaderGetterDecorator implements ReaderGetterable{
    protected ReaderGetterable readerGetter;

    public ReaderGetterDecorator(ReaderGetterable readerGetter){
        this.readerGetter = readerGetter;
    }

    public BufferedReader getBufferedReader(String resourceAddress) throws InnerResourceException{
        Reader reader = tryGetReader(resourceAddress);
        return new BufferedReader(reader);
    }

    private Reader tryGetReader(String resourceAddress) throws InnerResourceException {
        try {
            return this.getResourceReader(resourceAddress);
        }
        catch(Exception ex){
            return readerGetter.getResourceReader(resourceAddress);
        }
    }
}
