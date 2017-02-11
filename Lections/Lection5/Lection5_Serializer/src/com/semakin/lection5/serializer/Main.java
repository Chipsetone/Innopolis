package com.semakin.lection5.serializer;

import com.semakin.lection5.serializer.Objects.People;
import com.semakin.lection5.serializer.Objects.Student;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, TransformerException, ParserConfigurationException, IOException {
        ISerializatorable<String> serializator = new ReflectionSerializator();

        People people = new People("peopleName",100500, 35, "publicInfo");
        writeToXml(serializator, people, "People.xml");

        Student student = new Student("john doe", 18);
        writeToXml(serializator, student, "Student.xml");
    }

    private static void writeToXml(ISerializatorable<String> serializator, Object obj, String fileName) throws IOException, IllegalAccessException, TransformerException, ParserConfigurationException {
        String xmlObject = serializator.serialize(obj);
        System.out.println(xmlObject);
        writeToXml(xmlObject, fileName);
    }

    private static void writeToXml(String fileContent, String fileName) throws IOException {
        try(FileWriter fileWriter = new FileWriter(fileName)){
            fileWriter.write(fileContent);
            System.out.println(fileName + " has been written\n");
        }
    }
}
