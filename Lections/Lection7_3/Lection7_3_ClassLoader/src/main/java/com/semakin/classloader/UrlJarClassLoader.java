package com.semakin.classloader;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.jar.JarFile;
/**
 * @author Семакин Виктор
 */
public class UrlJarClassLoader extends ExternalClassLoader {
    private static final String urlForJarFile = "https://github.com/Chipsetone/Innopolis/blob/master/Lections/Lection7_3/Lection7_3_ClassLoader/out/artifacts/jar/Lection7_3_ClassLoaderOrigin.jar?raw=true";

    @Override
    protected JarFile getJarFile() throws IOException, URISyntaxException {
        return getJarFileFromUrlDirectDownload(urlForJarFile);
    }

    // способ 2 - с заморочками
    private JarFile getJarFileFromUrlDirectDownload(String url) throws URISyntaxException, IOException {
        JarFile jarFile = new JarFile(getFileFromHttp(url));
        return jarFile;
    }

    private File getFileFromHttp(String url) throws IOException {
        URL webSite = new URL(url);

        try (InputStream inputStream = webSite.openStream() ){
            final String tempFileName = "tempClassLoaderFile.jar";
            File fileResult = new File(tempFileName);
            try(FileOutputStream outputStream = new FileOutputStream(fileResult)) {
                pipeStreams(inputStream, outputStream);
            }
            return fileResult;
        }
    }

    private void pipeStreams(InputStream input, OutputStream output) throws IOException {
        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = input.read(bytes)) != -1) {
            output.write(bytes, 0, read);
        }
    }
}
