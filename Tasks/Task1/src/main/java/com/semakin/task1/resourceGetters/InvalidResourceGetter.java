package com.semakin.task1.resourceGetters;

import com.semakin.task1.exceptions.InnerResourceException;

import java.io.BufferedReader;
import java.io.Reader;

/**
 *  Конечный компонент для декоратора ReaderGetterable
 *  Вызывает исключение при попытке получения доступа к ресурсу
 * {@inheritDoc}
 * @see ReaderGetterable
 * @author Виктор Семакин
 */
public class InvalidResourceGetter implements ReaderGetterable{
    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedReader getBufferedReader(String resourceAddress) throws InnerResourceException {
        throw new InnerResourceException("Не удалось получить доступ к ресурсу " + resourceAddress);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Reader getResourceReader(String resourceAddress) throws InnerResourceException {
        throw new InnerResourceException("Не удалось получить доступ к ресурсу " + resourceAddress);
    }
}
