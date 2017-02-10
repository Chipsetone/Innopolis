package com.semakin.lection5.serializer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Created by Chi on 10.02.2017.
 */
public interface ISerializatorable<T> {
    T serialize(Object obj) throws IllegalAccessException, ParserConfigurationException, TransformerException;
}
