package com.semakin.labs.lab2.dbMarshallers;

import com.semakin.labs.lab2.XmlListEntities.IListEntities;
import com.semakin.labs.lab2.XmlListEntities.InterviewResultList;
import com.semakin.labs.lab2.XmlSerializer;
import com.semakin.labs.lab2.dao.IEntityQueryable;
import com.semakin.labs.lab2.entities.InterviewResult;

import java.util.List;

/**
 * @author Семакин Виктор
 */
public class InterviewResultDbMarshaller extends AbstractDbMarshaller<InterviewResult> {
    public InterviewResultDbMarshaller(XmlSerializer xmlSerializer) {
        super(xmlSerializer, InterviewResultList.class);
    }

    @Override
    public void marshalTable(IEntityQueryable<InterviewResult> entityDao, String filePath) {
        List<InterviewResult> entities = entityDao.selectAll();
        InterviewResultList entityList = new InterviewResultList();
        entityList.setList(entities);

        xmlSerializer.serializeToFile(getEntityListClass(), entityList, filePath);
    }

    @Override
    public IListEntities<InterviewResult> unmarshallTable(String filePath) {
        InterviewResultList interviewResultList = (InterviewResultList)xmlSerializer.deserializeFromFile(getEntityListClass(), filePath);

        return interviewResultList;
    }
}
