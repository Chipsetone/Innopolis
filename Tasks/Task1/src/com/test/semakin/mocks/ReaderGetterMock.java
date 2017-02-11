package com.test.semakin.mocks;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.resourceGetters.ReaderGetterable;
import sun.misc.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chi on 09.02.2017.
 */
public class ReaderGetterMock implements ReaderGetterable{
    private Map<String, String> readerVictimStub;

    public ReaderGetterMock() {
        this(new HashMap<>());
    }

    public ReaderGetterMock(Map<String, String> readerVictimStub){
        this.readerVictimStub = readerVictimStub;
    }

    @Override
    public BufferedReader getBufferedReader(String resourceAddress) throws InnerResourceException {
        return new BufferedReader(getResourceReader(resourceAddress));
    }

    @Override
    public Reader getResourceReader(String resourceAddress) throws InnerResourceException {
        InputStream inputStream = getInputStream(resourceAddress);
        return new InputStreamReader(inputStream);
    }

    private InputStream getInputStream(String resourceAddress) throws InnerResourceException {
        if(readerVictimStub == null){
            throw new InnerResourceException("ошибка ресурса");
        }
        String value = readerVictimStub.get(resourceAddress);
        if(value == null){
            throw new InnerResourceException("ошибка ресурса - не найден контент");
        }
        byte[] bytes = value.getBytes(Charset.defaultCharset());

        return new ByteArrayInputStream(bytes);
    }
}
