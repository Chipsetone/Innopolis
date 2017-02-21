package com.semakin.labs.lab2.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Семакин Виктор
 */
public abstract class AbstractDAO<T>{
    private ConnectionFactory connectionFactory;
    private Connection connection;

    public AbstractDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        this.connection = connectionFactory.getConnection();
    }

    protected PreparedStatement getPreparedStatement(String sqlQuery){
        try {
            return connection.prepareStatement(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO прилепить логгер
        }
        return null;
    }

    public void returnConnectionToPool(Connection connection) {
        connectionFactory.returnConnectionToPool(connection);
    }

    protected void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
