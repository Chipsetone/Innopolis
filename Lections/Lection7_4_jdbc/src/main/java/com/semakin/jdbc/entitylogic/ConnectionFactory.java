package com.semakin.jdbc.entitylogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Семакин Виктор
 */
public class ConnectionFactory {
    private static ConnectionFactory ourInstance = new ConnectionFactory();
    private Connection dbConnection;

    private static final String url = "jdbc:postgresql://localhost:5433/students";
    private static final String login = "postgres";
    private static final String password = "admin";

    public static ConnectionFactory getInstance() {
        return ourInstance;
    }

    private ConnectionFactory(){
        try {
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return dbConnection;
    }
}
