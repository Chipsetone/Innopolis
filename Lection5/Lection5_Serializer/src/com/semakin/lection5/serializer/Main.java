package com.semakin.lection5.serializer;

import com.semakin.lection5.serializer.Objects.People;
import com.semakin.lection5.serializer.Objects.Student;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Chi on 10.02.2017.
 */
public class Main {

    public static void main(String[] args) throws IllegalAccessException, TransformerException, ParserConfigurationException, IOException {
        ISerializatorable<String> serializator = new ReflectionSerializator();

        People people = new People("peopleName",100500, 35, "publicInfo");
        String xmlPeople = serializator.serialize(people);
        System.out.println(xmlPeople);
        writeToXml(xmlPeople, "People.xml");

        Student student = new Student("john doe", 18);
        String xmlStudent = serializator.serialize(student);
        System.out.println(xmlStudent);
        writeToXml(xmlStudent, "Student.xml");
    }

    private static void writeToXml(String fileContent, String fileName) throws IOException {
        try(FileWriter fileWriter = new FileWriter(fileName)){
            fileWriter.write(fileContent);
            System.out.println(fileName + " has been written\n");
        }
    }
}
