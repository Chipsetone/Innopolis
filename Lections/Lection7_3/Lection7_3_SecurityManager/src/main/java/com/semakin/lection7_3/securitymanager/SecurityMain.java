package com.semakin.lection7_3.securitymanager;

import java.io.IOException;

/**
 * @author Семакин Виктор
 */

public class SecurityMain {
    public static void main(String[] args) throws IOException {
        String fileName = "myFile.txt";
        MyWriter writer = new MyWriter(fileName);
        System.out.println("started");
        writer.writeMy(10);
        System.out.println("writed!");
        MyReader reader = new MyReader(fileName);

        int readedInt = reader.readMy();

        System.out.println("readed: " + readedInt);

        // TODO разобраться с read и write
    }
}
/*
-Djava.security.manager -Djava.security.policy="D:\\tempGit\\git\\Innopolis\\Lections\\Lection7_3\\Lection7_3_SecurityManager\\java.policy"
 */