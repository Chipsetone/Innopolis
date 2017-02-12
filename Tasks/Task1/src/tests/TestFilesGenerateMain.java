package tests;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Chi on 11.02.2017.
 */
public class TestFilesGenerateMain {

    public static void main(String[] args) {
        final int FILE_COUNT = 1000;

        FileGenerator generator = new FileGenerator(150);

        try {
            for (int i = 0; i < FILE_COUNT; i++) {
                    generator.generateValidFile("testGenarator" + i +".txt", 500);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
