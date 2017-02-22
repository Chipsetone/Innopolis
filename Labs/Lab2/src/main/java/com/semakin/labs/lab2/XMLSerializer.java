package com.semakin.labs.lab2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Xml-сериализатор
 * @author Семакин Виктор
 */
public class XMLSerializer {

    public void serializeToFile(Class objClass, Object object, String filePath){
        try {

            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(objClass);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(object, file);
            jaxbMarshaller.marshal(object, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Object deserializeFromFile(Class targetObjClass, String filePath){
        try {
            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(targetObjClass);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Object obj = jaxbUnmarshaller.unmarshal(file);
            return obj;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
