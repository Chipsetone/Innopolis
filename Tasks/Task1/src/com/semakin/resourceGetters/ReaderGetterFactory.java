package com.semakin.resourceGetters;

/**
 * Фабрика получателей ресурсов
 * @author Виктор Семакин
 */
public class ReaderGetterFactory {
    /**
     * Собирает декорированный ReaderGetter.
     * Порядок чтения им ресурсов:
     *  - сначала пытается получить доступ к локальному файлу,
     *  - затем к Http-ресурсу.
     *  - при неуспешных попытках подключения к Http- и File-ресурсам возникает исключение InnerResourceException
     * @return ReaderGetter - получатель читателя потока(Stream) -
     */
    public ReaderGetterable getReaderGetter(){
        ReaderGetterable invalidResource = new InvalidResourceGetter();
        ReaderGetterable httpReaderGetter = new HttpReaderGetterDecorator(invalidResource);
        ReaderGetterable fileReaderGetter = new FileReaderGetterDecorator(httpReaderGetter);

        return fileReaderGetter;
    }
}
