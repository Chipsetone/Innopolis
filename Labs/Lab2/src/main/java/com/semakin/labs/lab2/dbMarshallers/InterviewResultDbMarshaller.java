package com.semakin.labs.lab2.dbMarshallers;

import com.semakin.labs.lab2.XmlListEntities.InterviewResultList;
import com.semakin.labs.lab2.XmlSerializer;
import com.semakin.labs.lab2.dao.IEntityQueryable;
import com.semakin.labs.lab2.entitiessimple.InterviewResult;

import java.util.List;

/**
 * @author Семакин Виктор
 */
public class InterviewResultDbMarshaller extends AbstractDbMarshaller<InterviewResult> {
    public InterviewResultDbMarshaller(XmlSerializer xmlSerializer) {
        super(xmlSerializer, InterviewResultList.class);
    }

    public void marshalTable(IEntityQueryable<InterviewResult> entityDao, String filePath) {
        List<InterviewResult> entities = entityDao.selectAll();
        InterviewResultList entityList = new InterviewResultList();
        entityList.setInterviewResults(entities);

        xmlSerializer.serializeToFile(getEntityListClass(), entityList, filePath);
    }
}
