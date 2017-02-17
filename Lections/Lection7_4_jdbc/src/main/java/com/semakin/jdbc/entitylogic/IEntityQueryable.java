package com.semakin.jdbc.entitylogic;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public interface IEntityQueryable<T extends Entity> {
    void insert(T entity) throws SQLException, IllegalAccessException;
    T selectById(long id) throws SQLException;
    List<T> selectAll();
    void update(T entity);
    void deleteById(T entity);
}
