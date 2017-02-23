package com.semakin.labs.lab2.connection;

import java.sql.Connection;

/**
 *
 * @author Семакин Виктор
 */
public interface IConnectionFactory {
    Connection getConnection();

    //void returnConnectionToPool(Connection connection);
}
