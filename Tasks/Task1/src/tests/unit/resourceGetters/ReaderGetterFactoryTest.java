package tests.unit.resourceGetters;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.resourceGetters.FileReaderGetterDecorator;
import com.semakin.resourceGetters.HttpReaderGetterDecorator;
import com.semakin.resourceGetters.ReaderGetterFactory;
import com.semakin.resourceGetters.ReaderGetterable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.Buffer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Chi on 09.02.2017.
 */
class ReaderGetterFactoryTest {
    @Test
    void getReaderGetter_InvalidResource_Exception() {
        ReaderGetterFactory factory = getReaderGetterFactory();
        String invalidResource = "jkhas&*^(*%#@#)/']'].[;";

        ReaderGetterable readerGetter = factory.getReaderGetter();
        try(Reader reader = readerGetter.getBufferedReader(invalidResource)) {

        } catch (InnerResourceException e) {
            System.out.println("то что нужно - исключение");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.fail("ожидался InnerResourceException");
    }

    @Test
    void getReaderGetter_validHttpResource() throws InnerResourceException {
        ReaderGetterFactory factory = getReaderGetterFactory();
        String ratesCBUrl = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=02/03/2002"; // тестим HTTP получением курсов центробанка. Да, может отвалиться, но тем не менее достаточно надежен.

        ReaderGetterable readerGetter = factory.getReaderGetter();

        try(Reader actualReader = readerGetter.getBufferedReader(ratesCBUrl)){
            System.out.println("ok");
            return;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.fail("Здесь явно то не это и это не то.");
    }

    @Test
    void getReaderGetter_validFileResource() throws InnerResourceException {
        ReaderGetterFactory factory = getReaderGetterFactory();
        String filePath = "C:\\temp\\forTest.txt";
        ReaderGetterable readerGetter = factory.getReaderGetter();

        try(Reader actualReader = readerGetter.getBufferedReader(filePath)){
            System.out.println("ok");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.fail("Здесь явно то не это и это не то.");
    }


    private ReaderGetterFactory getReaderGetterFactory(){
        return new ReaderGetterFactory();
    }
}