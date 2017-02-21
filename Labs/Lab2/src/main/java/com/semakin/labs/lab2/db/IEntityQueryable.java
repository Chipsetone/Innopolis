package com.semakin.labs.lab2.db;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public interface IEntityQueryable<T> {
    T selectById(long id) throws SQLException, IllegalAccessException, NoSuchFieldException;
    List<T> selectAll() throws SQLException, NoSuchFieldException, IllegalAccessException;
    void insert(T entity) throws SQLException, IllegalAccessException;
//    void update(T entity) throws SQLException, IllegalAccessException;
    void deleteById(long id) throws SQLException;
}
