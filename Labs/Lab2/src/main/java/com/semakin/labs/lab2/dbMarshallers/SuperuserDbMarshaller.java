package com.semakin.labs.lab2.dbMarshallers;

import com.semakin.labs.lab2.XmlListEntities.SuperuserList;
import com.semakin.labs.lab2.XmlSerializer;
import com.semakin.labs.lab2.dao.IEntityQueryable;
import com.semakin.labs.lab2.entities.Superuser;

import java.util.List;

/**
 * @author Семакин Виктор
 */
public class SuperuserDbMarshaller extends AbstractDbMarshaller<Superuser>{
    public SuperuserDbMarshaller(XmlSerializer xmlSerializer) {
        super(xmlSerializer, SuperuserList.class);
    }

    @Override
    public void marshalTable(IEntityQueryable<Superuser> entityDao, String filePath) {
        List<Superuser> entities = entityDao.selectAll();
        SuperuserList entityList = new SuperuserList();
        entityList.setSuperusers(entities);

        xmlSerializer.serializeToFile(getEntityListClass(), entityList, filePath);
    }

    @Override
    public void unmarshallTable(String filePath) {
        SuperuserList superuserList = (SuperuserList)xmlSerializer.deserializeFromFile(getEntityListClass(), filePath);

        for (Superuser item :
                superuserList.getSuperusers()) {
            System.out.println(item);
        }
    }
}
