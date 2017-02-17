package com.semakin.labs.lab1tests;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileGenerator{
    private Random random = new Random();
    private int maxIntBound;

    public FileGenerator(int maxIntBound) {
        this.maxIntBound = maxIntBound;
    }

    public void generateValidFile(String fileName, int countOfGeneratedNumbersInFile) throws IOException {
        try(FileWriter fileWriter = new FileWriter(fileName)) {
            for (int i = 0; i < countOfGeneratedNumbersInFile; i++) {
                int number = getRandomNumber();
                fileWriter.write(number + " ");
            }
        }
        System.out.println(fileName + " generated");
    }

    private Integer getRandomNumber(){
        return random.nextInt(maxIntBound) - random.nextInt(maxIntBound / 2);
    }
}
