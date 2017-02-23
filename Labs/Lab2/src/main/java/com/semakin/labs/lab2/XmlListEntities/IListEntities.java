package com.semakin.labs.lab2.XmlListEntities;

import java.util.List;

/**
 * @author Семакин Виктор
 */
public interface IListEntities<T> {
    List<T> getList();

    void setList(List<T> userList);
}
