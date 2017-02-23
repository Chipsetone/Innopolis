package com.semakin.labs.lab2.dbMarshallers;

import com.semakin.labs.lab2.XmlListEntities.IListEntities;
import com.semakin.labs.lab2.XmlListEntities.InterviewList;
import com.semakin.labs.lab2.XmlSerializer;
import com.semakin.labs.lab2.dao.IEntityQueryable;
import com.semakin.labs.lab2.entities.Interview;

import java.util.List;

/**
 * @author Семакин Виктор
 */
public class InterviewDbMarshaller extends AbstractDbMarshaller<Interview>{

    public InterviewDbMarshaller(XmlSerializer xmlSerializer) {
        super(xmlSerializer, InterviewList.class);
    }

    @Override
    public void marshalTable(IEntityQueryable<Interview> entityDao, String filePath) {
        List<Interview> entities = entityDao.selectAll();
        InterviewList entityList = new InterviewList();
        entityList.setList(entities);

        xmlSerializer.serializeToFile(getEntityListClass(), entityList, filePath);
    }
    @Override
    public IListEntities<Interview> unmarshallTable(String filePath){
        InterviewList interviewList = (InterviewList) xmlSerializer.deserializeFromFile(getEntityListClass(), filePath);

        return interviewList;
    }
}
