package com.semakin.resourceGetters;

import com.semakin.exceptions.InnerResourceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by Chi on 07.02.2017.
 */
@Deprecated
public class ResourceStringGetter{
    ReaderGetterable readerGetter;

    public ResourceStringGetter(ReaderGetterable readerGetter){
        this.readerGetter = readerGetter;
    }

    public String getResourceString(String resourceAddress) throws InnerResourceException {
        try(Reader fileReader = getBufferedReader(resourceAddress)) {
            int charCode;
            StringBuilder result = new StringBuilder();

            while((charCode=fileReader.read())!=-1){
                result.append((char)charCode);
            }
            return result.toString();
        }
        catch(IOException ex){
            throw new InnerResourceException("Ошибка ввода-вывода", ex);
        }
    }

    private BufferedReader getBufferedReader(String resourceAddress) throws InnerResourceException {
        return readerGetter.getBufferedReader(resourceAddress);
    }
}
