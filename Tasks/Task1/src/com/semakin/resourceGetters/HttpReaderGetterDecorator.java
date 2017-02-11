package com.semakin.resourceGetters;

import com.semakin.exceptions.InnerResourceException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Chi on 06.02.2017.
 */
public class HttpReaderGetterDecorator extends ReaderGetterDecorator {
    public HttpReaderGetterDecorator(ReaderGetterable readerGetter) {
        super(readerGetter);
    }

    @Override
    public final Reader getResourceReader(String resourceAddress) throws InnerResourceException {
        try{
            InputStream inputStream = getInputStream(resourceAddress);
            return new InputStreamReader(inputStream);
        }
        catch(IOException ex){
            throw new InnerResourceException("Ошибка при работе с удаленным узлом сети", ex);
        }
    }

    private InputStream getInputStream(String resourceAddress) throws MalformedURLException, IOException {
        URL url = new URL(resourceAddress);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        return conn.getInputStream();
    }
}
