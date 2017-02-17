package com.semakin.labs.lab1tests.unit.resourceGetters;

import com.semakin.labs.lab1.resourceGetters.FileReaderGetterDecorator;
import com.semakin.labs.lab1.resourceGetters.InvalidResourceGetter;
import com.semakin.labs.lab1.resourceGetters.ReaderGetterable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Reader;

/**
 * Created by Chi on 07.02.2017.
 */
class FileReaderGetterTest {

    @Test
    void getResourceReader() throws Exception {
        final String VALID_FILE_PATH =  "TestLocalData\\validData0.txt"; // нужен такой файл и всё тут. можно конечно поменять =)
        ReaderGetterable invalidResourceGetter = new InvalidResourceGetter();
        FileReaderGetterDecorator fileReaderGetter = new FileReaderGetterDecorator(invalidResourceGetter);

        try(Reader actualReader =  fileReaderGetter.getBufferedReader(VALID_FILE_PATH)){
            Assertions.assertNotNull(actualReader);
        }
    }
}