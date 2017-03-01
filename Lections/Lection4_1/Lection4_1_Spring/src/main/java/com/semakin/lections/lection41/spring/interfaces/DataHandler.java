package com.semakin.lections.lection41.spring.interfaces;

/**
 * @author Семакин Виктор
 */
public class DataHandler {

    private Downloader downloader;

    private Uploader uploader;

    public DataHandler(){
 /*Жесткое создание полей*/
    }
    public void hadleData(String srcPath, String destPath){
        String content = this.downloader.download(srcPath);
        String handledContent = handle(content);
        this.uploader.upload(destPath, handledContent);
    }

    private String handle(String content) {
        return "";
    }

    public void setUploader(Uploader uploader) {
        this.uploader = uploader;
    }
}
