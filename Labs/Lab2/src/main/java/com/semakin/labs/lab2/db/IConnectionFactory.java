package com.semakin.labs.lab2.db;

import java.sql.Connection;

/**
 *
 * @author Семакин Виктор
 */
public interface IConnectionFactory {
    Connection getConnection();

    void returnConnectionToPool(Connection connection);
}
