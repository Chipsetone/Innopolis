package com.semakin.jdbc.entitylogic;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public interface IEntityQueryable<T extends Entity> {
    T selectById(long id) throws SQLException, IllegalAccessException, NoSuchFieldException;
    List<T> selectByName(String name) throws SQLException, NoSuchFieldException, IllegalAccessException;
    List<T> selectAll() throws SQLException, NoSuchFieldException, IllegalAccessException;
    void insert(T entity) throws SQLException, IllegalAccessException;
    void update(T entity) throws SQLException, IllegalAccessException;
    void deleteById(long id) throws SQLException;
    void delete(T entity) throws SQLException;
}
