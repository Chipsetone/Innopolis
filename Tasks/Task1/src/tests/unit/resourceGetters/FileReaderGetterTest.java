package tests.unit.resourceGetters;

import com.semakin.resourceGetters.FileReaderGetterDecorator;
import com.semakin.resourceGetters.InvalidResourceGetter;
import com.semakin.resourceGetters.ReaderGetterable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Reader;

/**
 * Created by Chi on 07.02.2017.
 */
class FileReaderGetterTest {

    @Test
    void getResourceReader() throws Exception {
        final String VALID_FILE_PATH = "C:\\temp\\forTest.txt"; // нужен такой файл и всё тут. можно конечно поменять =)
        ReaderGetterable invalidResourceGetter = new InvalidResourceGetter();
        FileReaderGetterDecorator fileReaderGetter = new FileReaderGetterDecorator(invalidResourceGetter);

        try(Reader actualReader =  fileReaderGetter.getBufferedReader(VALID_FILE_PATH)){
            Assertions.assertNotNull(actualReader);
        }
    }
}