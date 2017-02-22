package com.semakin.labs.lab2.dbMarshallers;

import com.semakin.labs.lab2.XmlSerializer;

/**
 * @author Семакин Виктор
 */
public abstract class AbstractDbMarshaller<T> {
    protected XmlSerializer xmlSerializer;
    protected Class entityListClass;

    public AbstractDbMarshaller(XmlSerializer xmlSerializer, Class listClass) {
        this.xmlSerializer = xmlSerializer;
        this.entityListClass = listClass;
    }

    protected Class getEntityListClass(){
        return entityListClass;
    }
}
