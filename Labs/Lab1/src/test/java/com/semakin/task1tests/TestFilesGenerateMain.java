package com.semakin.task1tests;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Вспомогательный модуль.
 * Генерирует валидные файлы для тестирования программы.
 * Количество файлов задается в FILE_COUNT
 */
public class TestFilesGenerateMain {

    public static void main(String[] args) {
        final int FILE_COUNT = 1000;

        FileGenerator generator = new FileGenerator(150);

        try(FileWriter fileListWriter = new FileWriter("ValidFileNames.txt")) {
            for (int i = 0; i < FILE_COUNT; i++) {
                String fileName = "validData" + i +".txt";
                generator.generateValidFile(fileName, 500);

                fileListWriter.write(fileName + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
