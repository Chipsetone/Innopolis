package com.semakin.labs.lab1.resourceGetters;

import com.semakin.labs.lab1.exceptions.InnerResourceException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

//    @Override
//    public BufferedReader getBufferedReader(String resourceAddress) throws InnerResourceException {
//        return (BufferedReader)getResourceReader(resourceAddress);
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Reader getResourceReader(String resourceAddress) throws InnerResourceException {
        try{
            return new FileReader(resourceAddress);
//            Path filePath = Paths.get(resourceAddress);
//            return Files.newBufferedReader(filePath);
        }
        catch(Exception ex){
            throw new InnerResourceException("Ошибка при работе с ресурсным файлом", ex);
        }
    }
}
