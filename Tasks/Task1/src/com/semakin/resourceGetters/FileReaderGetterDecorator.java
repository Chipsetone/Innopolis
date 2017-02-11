package com.semakin.resourceGetters;

import com.semakin.exceptions.InnerResourceException;

import java.io.FileReader;
import java.io.Reader;

/**
 * Created by Chi on 07.02.2017.
 */
public class FileReaderGetterDecorator extends ReaderGetterDecorator {
    public FileReaderGetterDecorator(ReaderGetterable readerGetter) {
        super(readerGetter);
    }

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
