package tests.unit.resourceGetters;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.resourceGetters.HttpReaderGetterDecorator;
import com.semakin.resourceGetters.InvalidResourceGetter;
import com.semakin.resourceGetters.ReaderGetterable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.Reader;

/**
 * Created by Chi on 06.02.2017.
 */
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