package com.semakin.lection7_3.securitymanager;

import java.io.IOException;

/**
 * @author Семакин Виктор
 */

public class SecurityMain {
    public static void main(String[] args) throws IOException {
        String fileName = "myFile.txt";
        MyWriter writer = new MyWriter(fileName);

        writer.writeMy(10);

        MyReader reader = new MyReader(fileName);

        int readedInt = reader.readMy();

        System.out.println(readedInt);
    }
}
