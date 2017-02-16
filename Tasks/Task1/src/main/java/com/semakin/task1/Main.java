package com.semakin.task1;

import org.apache.log4j.xml.DOMConfigurator;

/*Необходимо разработать программу,
    которая получает на вход список ресурсов,
    содержащих набор чисел и считает сумму всех положительных четных.
    Каждый ресурс должен быть обработан в отдельном потоке,
    набор должен содержать лишь числа, унарный оператор "-" и пробелы.
    Общая сумма должна отображаться на экране и изменяться в режиме реального времени.
    Все ошибки должны быть корректно обработаны, все API покрыто модульными тестами*/
public class Main {
    private static final String loggerConfigFile = "src/main/resources/log4j.xml";

    static {
        DOMConfigurator.configure(loggerConfigFile);
    }

    public static void main(String[] args) {
        // в папке тестов есть генератор валидных файлов, запускаемый из TestFilesGenerateMain.java

        ResultPrinter printer = new ResultPrinter();
        ApplicationFacade application = new ApplicationFacade(printer);
        application.Run(args);
    }
}
