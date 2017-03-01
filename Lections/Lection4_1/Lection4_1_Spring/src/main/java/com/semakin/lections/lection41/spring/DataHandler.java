package com.semakin.lections.lection41.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Семакин Виктор
 */
@Component
public class DataHandler {
    @Autowired
    private Downloader downloader;

    @Autowired
    private Uploader uploader;

    public DataHandler(){
//        downloader = new FileDownloader();
//        uploader = new DbUploader();
    }

    public DataHandler(Downloader downloader, Uploader uploader) {
        this.downloader = downloader;
        this.uploader = uploader;
    }

    public void hadleData(String srcPath, String destPath){
        String content = this.downloader.download(srcPath);
        String handledContent = handle(content);
        this.uploader.upload(destPath, handledContent);
    }

    private String handle(String content) {
        return "";
    }

    public void setDownloader(Downloader downloader) {
        this.downloader = downloader;
    }

    public void setUploader(Uploader uploader) {
        this.uploader = uploader;
    }
}
