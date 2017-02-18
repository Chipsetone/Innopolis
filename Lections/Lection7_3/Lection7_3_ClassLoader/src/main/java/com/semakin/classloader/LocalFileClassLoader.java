package com.semakin.classloader;

import java.io.IOException;
import java.util.jar.JarFile;

/**
 * @author Семакин Виктор
 */
public class LocalFileClassLoader extends ExternalClassLoader {
//    private static final String filePath = "D:\\tempGit\\git\\Innopolis\\Lections\\Lection7_3\\Lection7_3_ClassLoader\\out\\artifacts\\jar\\Lection7_3_ClassLoader.jar";
    private static final String filePath = "D:\\git\\Innopolis\\Lections\\Lection7_3\\Lection7_3_ClassLoader\\out\\artifacts\\jar\\Lection7_3_ClassLoader.jar";

    @Override
    protected JarFile getJarFile() throws IOException {
        return getJarFileLocal(filePath);
    }

    // для файла
    private JarFile getJarFileLocal(String fileName) throws IOException {
        return new JarFile(fileName);
    }
}
