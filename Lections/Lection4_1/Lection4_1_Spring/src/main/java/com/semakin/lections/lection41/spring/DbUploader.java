package com.semakin.lections.lection41.spring;

import org.springframework.stereotype.Component;

/**
 * @author Семакин Виктор
 */

public class DbUploader implements Uploader {
    public boolean upload(String path, Object content) {
        System.out.println("upload to db");
        return true;
    }
}
