package models.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public interface IEntityQueryable<T> {
    T selectById(long id);
    List<T> selectAll();
    void insert(T entity) throws SQLException;
    void deleteById(long id) throws SQLException;
}
