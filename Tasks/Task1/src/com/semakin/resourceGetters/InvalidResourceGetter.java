package com.semakin.resourceGetters;

import com.semakin.exceptions.InnerResourceException;

import java.io.BufferedReader;
import java.io.Reader;

/**
 * Created by Chi on 09.02.2017.
 */
public class InvalidResourceGetter implements ReaderGetterable{
    @Override
    public BufferedReader getBufferedReader(String resourceAddress) throws InnerResourceException {
        throw new InnerResourceException("Не удалось получить доступ к ресурсу " + resourceAddress);
    }

    @Override
    public Reader getResourceReader(String resourceAddress) throws InnerResourceException {
        throw new InnerResourceException("Не удалось получить доступ к ресурсу " + resourceAddress);
    }
}
