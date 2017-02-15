package com.semakin.classloader;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.jar.JarFile;

/**
 * @author Семакин Виктор
 */
public class UrlJarClassLoader extends ExternalClassLoader {
    private static final String gitArtifactsRepoUrl = "https://github.com/Chipsetone/Innopolis/blob/master/Lections/Lection7_3/Lection7_3_ClassLoader/out/artifacts/jar/";
    private static final String urlForJarFile = gitArtifactsRepoUrl + "Lection7_3_ClassLoader.jar?raw=true";

    @Override
    protected JarFile getJarFile() throws IOException {
        return getJarFileFromUrl(urlForJarFile);
    }

    private JarFile getJarFileFromUrl(String url) throws IOException {
        JarURLConnection connection = getJarUrlConnection(url);
        return connection.getJarFile();
    }

    private JarURLConnection getJarUrlConnection(String url) throws IOException {
        final URL jarUrl = new URL(url);

        return (JarURLConnection) jarUrl.openConnection();
    }
}
