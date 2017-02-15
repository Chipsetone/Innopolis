package com.semakin.lection7_3.securitymanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Семакин Виктор
 */

public class MyReader {
    private String fileName;

    public MyReader(String fileName) {
        this.fileName = fileName;
    }

    public int readMy() throws IOException {
        try(InputStream inputStream = new FileInputStream("myFile.txt")){
            return inputStream.read();
        }
    }
}
