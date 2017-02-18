package com.semakin.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Hashtable;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Семакин Виктор
 */
public abstract class ExternalClassLoader extends ClassLoader{
    private Hashtable classes = new Hashtable(); //used to cache already defined classes

    public ExternalClassLoader() {
        super(UrlJarClassLoader.class.getClassLoader()); //calls the parent class loader's constructor
    }

    @Override
    public Class loadClass(String className) throws ClassNotFoundException {
        return findClass(className);
    }

    @Override
    public Class findClass(String className) {
        byte classByte[];
        Class result;

        try {
            JarFile jar = getJarFile();
            JarEntry entry = jar.getJarEntry(className + ".class");
            InputStream is = jar.getInputStream(entry);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            int nextValue = is.read();
            while (-1 != nextValue) {
                byteStream.write(nextValue);
                nextValue = is.read();
            }

            classByte = byteStream.toByteArray();
            result = defineClass(className, classByte, 0, classByte.length, null);
            classes.put(className, result);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private Class getCachedClass(String className){
        return (Class) classes.get(className);
    }

    protected abstract JarFile getJarFile() throws IOException, URISyntaxException;
}
