package com.semakin.resourceGetters;

/**
 * @au
 */
public class ReaderGetterFactory {
    /**
     * Собирает декорированный ReaderGetter.
     * Порядок чтения им ресурсов:
     *  - сначала пытается получить доступ к локальному файлу,
     *  - затем к Http-ресурсу.
     *  - при неуспешных попытках подключения к Http- и File-ресурсам возникает исключение InnerResourceException
     * @return Получатель читателя потока(Stream) - ReaderGetterable
     */
    public ReaderGetterable getReaderGetter(){
        ReaderGetterable invalidResource = new InvalidResourceGetter();
        ReaderGetterable httpReaderGetter = new HttpReaderGetterDecorator(invalidResource);
        ReaderGetterable fileReaderGetter = new FileReaderGetterDecorator(httpReaderGetter);

        return fileReaderGetter;
    }
}
