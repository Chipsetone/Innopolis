package com.semakin.lections.lection41.spring;

import org.springframework.stereotype.Component;

/**
 * @author Семакин Виктор
 */
@Component
public class FileDownloader implements Downloader {
    public String download(String path) {
        return "String from file";
    }
}
