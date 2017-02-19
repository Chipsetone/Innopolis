package com.semakin.classloadertests;

import com.semakin.classloader.UrlJarClassLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Семакин Виктор
 */
class UrlJarClassLoaderTest {
    @Test
    void getJarFile() {
        UrlJarClassLoader urlLoader = new UrlJarClassLoader();

        Class actual = urlLoader.findClass("Animal");

        Assertions.fail("Дошло до конца");
    }
    // TODO запилить создание экземпляра и сериализацию в XML
}