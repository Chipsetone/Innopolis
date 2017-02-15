package com.semakin.classloadertests;

import com.semakin.classloader.LocalFileClassLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Семакин Виктор
 */
class LocalFileClassLoaderTest {
    @Test
    void getJarFile() {
        LocalFileClassLoader fileLoader = new LocalFileClassLoader();

        Class actual = fileLoader.findClass("Animal");

        Assertions.fail("Дошло до конца");
    }

}