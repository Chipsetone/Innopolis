package com.semakin.labs.lab2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Семакин Виктор
 */
public class ConnectionFactory implements IConnectionFactory {
    private static ConnectionFactory ourInstance = new ConnectionFactory();
    private Connection dbConnection;

    //private ConnectionPool connectionPool;

    private static final String url = "jdbc:postgresql://localhost:5432/stc";
    private static final String login = "postgres";
    private static final String password = "admin";



    public static ConnectionFactory getInstance() {
        return ourInstance;
    }

    private ConnectionFactory(){


        try {
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(url, login, password);
            // TODO инициализировать ConnectionPool по url, логину и паролю

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return dbConnection;
    }

    public void returnConnectionToPool(Connection connection) {
        throw new UnsupportedOperationException("реализовать это!");
    }
}
