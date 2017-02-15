package com.semakin.lection7_3.securitymanager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Семакин Виктор
 */

public class MyWriter {
    private String fileName;

    public MyWriter(String fileName) {
        this.fileName = fileName;
    }

    public void writeMy(int i) throws IOException {
        try(OutputStream outputStream = new FileOutputStream("myFile.txt")){
            outputStream.write(i);
        }
    }
}
