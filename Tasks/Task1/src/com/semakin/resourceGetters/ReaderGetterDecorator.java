package com.semakin.resourceGetters;

import com.semakin.exceptions.InnerResourceException;

import java.io.BufferedReader;
import java.io.Reader;

/**
 * Шаблон декоратор для получателя ресурсов
 * Необходим для распознавания ресурса по его имени.
 * При невозможности получить ресурс с помощью текущего ReaderGetterable-объекта
 * будет пытаться получить доступ к ресурсу с помощью ReaderGetterable-объекта,
 * переданного в конструкторе.
 * @see ReaderGetterable
 * {@inheritDoc}
 * @author Виктор Семакин
 */
public abstract class ReaderGetterDecorator implements ReaderGetterable{
    private ReaderGetterable readerGetter;

    /**
     * Конструктор
     * @param readerGetter ReaderGetterable, с помощью которого будет получен доступ к ресурсу
     *                     при отказе получения доступа с помощью текущего объекта
     */
    public ReaderGetterDecorator(ReaderGetterable readerGetter){
        this.readerGetter = readerGetter;
    }

    /**
     * {@inheritDoc}
     */
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
