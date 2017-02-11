package com.test.semakin.integration;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.resourceGetters.*;
import com.test.semakin.mocks.ReaderGetterMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chi on 08.02.2017.
 */
class ResourceStringGetterTest{

    @Test
    void getResourceString_HttpReader_validStringContent() throws InnerResourceException {
        String resourceName = "someResourceName";
        String expectedPart = "xml version=\"1.0\"";
        Map<String, String> readerStub = new HashMap<>();
        readerStub.put(resourceName, "content head " + expectedPart + "content tail");

        ReaderGetterable httpReaderGetter = getReaderGetterMock(readerStub);
        ResourceStringGetter stringGetter = new ResourceStringGetter(httpReaderGetter);
        String actualResult = "";

        try {
            actualResult = stringGetter.getResourceString(resourceName);
        }
        catch(Exception ex){
            logString("Error: " + ex.getMessage());
        }
        Assertions.assertTrue(actualResult.indexOf(expectedPart) > 0);
    }

    private ReaderGetterable getReaderGetterMock(Map<String, String> readerStub){
        return new ReaderGetterMock(readerStub);
    }

    private void logString(String message){
        System.out.println(message);
    }
}