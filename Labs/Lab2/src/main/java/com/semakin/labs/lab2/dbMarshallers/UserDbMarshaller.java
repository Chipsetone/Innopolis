package com.semakin.labs.lab2.dbMarshallers;

import com.semakin.labs.lab2.XmlListEntities.IListEntities;
import com.semakin.labs.lab2.XmlSerializer;
import com.semakin.labs.lab2.dao.IEntityQueryable;
import com.semakin.labs.lab2.XmlListEntities.UserList;
import com.semakin.labs.lab2.entities.User;

import java.util.List;

/**
 * @author Семакин Виктор
// */
public class UserDbMarshaller extends AbstractDbMarshaller<User>{

    public UserDbMarshaller(XmlSerializer xmlSerializer) {
        super(xmlSerializer, UserList.class);
    }

    @Override
    public void marshalTable(IEntityQueryable<User> entityDao, String filePath) {
        List<User> entities = entityDao.selectAll();
        UserList entityList = new UserList();
        entityList.setList(entities);

        xmlSerializer.serializeToFile(getEntityListClass(), entityList, filePath);
    }

    @Override
    public IListEntities<User> unmarshallTable(String filePath) {
        UserList userList = (UserList)xmlSerializer.deserializeFromFile(getEntityListClass(), filePath);

        return userList;
    }

//    public void
}
