package com.semakin.labs.lab2.dbMarshallers;

import com.semakin.labs.lab2.XmlSerializer;
import com.semakin.labs.lab2.dao.IEntityQueryable;
import com.semakin.labs.lab2.XmlListEntities.UserList;
import com.semakin.labs.lab2.entitiessimple.User;

import java.util.List;

/**
 * @author Семакин Виктор
// */
public class UserDbMarshaller extends AbstractDbMarshaller<User>{

    public UserDbMarshaller(XmlSerializer xmlSerializer) {
        super(xmlSerializer, UserList.class);
    }

    public void marshalTable(IEntityQueryable<User> entityDao, String filePath) {
        List<User> entities = entityDao.selectAll();
        UserList entityList = new UserList();
        entityList.setUserList(entities);

        xmlSerializer.serializeToFile(getEntityListClass(), entityList, filePath);
    }
}
