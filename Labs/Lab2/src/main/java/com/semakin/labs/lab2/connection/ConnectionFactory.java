package com.semakin.labs.lab2.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Семакин Виктор
 */
public class ConnectionFactory implements IConnectionFactory {
    private static final Logger logger = Logger.getLogger(ConnectionFactory.class);
    private static final IConnectionFactory ourInstance = new ConnectionFactory();

    private static final String url = "jdbc:postgresql://localhost:5432/stc";
    private static final String login = "postgres";
    private static final String password = "admin";
    private static final String className = "org.postgresql.Driver";

    public static IConnectionFactory getInstance() {
        return ourInstance;
    }

    private ConnectionFactory(){
    }

    public Connection getConnection(){
        try {
            Class.forName(className);
            return DriverManager.getConnection(url, login, password);
            // TODO инициализировать ConnectionPool по url, логину и паролю

        } catch (ClassNotFoundException | SQLException e) {
           logger.error(e);
        }
        return null;
    }
}
