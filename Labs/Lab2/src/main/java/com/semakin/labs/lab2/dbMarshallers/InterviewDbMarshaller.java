package com.semakin.labs.lab2.dbMarshallers;

import com.semakin.labs.lab2.XmlListEntities.InterviewList;
import com.semakin.labs.lab2.XmlSerializer;
import com.semakin.labs.lab2.dao.IEntityQueryable;
import com.semakin.labs.lab2.entitiessimple.Interview;

import java.util.List;

/**
 * @author Семакин Виктор
 */
public class InterviewDbMarshaller extends AbstractDbMarshaller<Interview>{

    public InterviewDbMarshaller(XmlSerializer xmlSerializer) {
        super(xmlSerializer, InterviewList.class);
    }

    public void marshalTable(IEntityQueryable<Interview> entityDao, String filePath) {
        List<Interview> entities = entityDao.selectAll();
        InterviewList entityList = new InterviewList();
        entityList.setInterviewList(entities);

        xmlSerializer.serializeToFile(getEntityListClass(), entityList, filePath);
    }
}
