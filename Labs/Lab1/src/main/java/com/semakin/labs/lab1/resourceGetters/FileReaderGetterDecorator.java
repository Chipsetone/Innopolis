package com.semakin.labs.lab1.resourceGetters;

import com.semakin.labs.lab1.exceptions.InnerResourceException;

import java.io.FileReader;
import java.io.Reader;

/**
 * Получатель доступа к ресурсу файловой системы
 * {@inheritDoc}
 * @see ReaderGetterDecorator
 * @author Виктор Семакин
 */
public class FileReaderGetterDecorator extends ReaderGetterDecorator {
    /**
     * {@inheritDoc}
     */
    public FileReaderGetterDecorator(ReaderGetterable readerGetter) {
        super(readerGetter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Reader getResourceReader(String resourceAddress) throws InnerResourceException {
        try{
            return new FileReader(resourceAddress);
        }
        catch(Exception ex){
            throw new InnerResourceException("Ошибка при работе с ресурсным файлом", ex);
        }
    }
}
