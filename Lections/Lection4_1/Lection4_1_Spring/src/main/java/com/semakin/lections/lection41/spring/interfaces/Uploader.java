package com.semakin.lections.lection41.spring.interfaces;

/**
 * @author Семакин Виктор
 */
public interface Uploader {

    /**
     *
     * @param path
     * @param content
     * @return
     */
    public boolean upload(String path, Object content);
}
