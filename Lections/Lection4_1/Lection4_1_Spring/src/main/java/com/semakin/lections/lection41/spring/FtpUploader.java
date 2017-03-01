package com.semakin.lections.lection41.spring;

/**
 * @author Семакин Виктор
 */
public class FtpUploader implements Uploader {
    public boolean upload(String path, Object content) {
        System.out.println("upload to ftp");
        return true;
    }
}
