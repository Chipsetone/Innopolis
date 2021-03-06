package com.semakin.labs.lab1tests.unit.resourceGetters;

import com.semakin.labs.lab1.exceptions.InnerResourceException;
import com.semakin.labs.lab1.resourceGetters.HttpReaderGetterDecorator;
import com.semakin.labs.lab1.resourceGetters.InvalidResourceGetter;
import com.semakin.labs.lab1.resourceGetters.ReaderGetterable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Reader;

class HttpReaderGetterTest {
    @Test
    void getResourceReader_alwaysValidResource_somethingStringResult() throws InnerResourceException {

        ReaderGetterable invalidResourceGetter = new InvalidResourceGetter();
        HttpReaderGetterDecorator httpGetter = new HttpReaderGetterDecorator(invalidResourceGetter);
        String urlRatesCB = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=02/03/2002"; // тестим HTTP получением курсов центробанка. Да, может отвалиться, но тем не менее достаточно надежен.

        Reader httpReader = httpGetter.getBufferedReader(urlRatesCB);

        Assertions.assertNotNull(httpReader);
    }
}