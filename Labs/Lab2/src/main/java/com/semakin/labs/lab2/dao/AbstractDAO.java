package com.semakin.labs.lab2.dao;

import com.semakin.labs.lab2.db.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public abstract class AbstractDAO<T> implements IEntityQueryable<T> {
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private static final String SELECT_QUERY = "SELECT * FROM ";

    public AbstractDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        this.connection = connectionFactory.getConnection();
    }

    protected PreparedStatement getPreparedStatement(String sqlQuery){
        try {
            return connection.prepareStatement(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO прилепить логгер не забыть
        }
        return null;
    }

    // TODO использовать
//    public void returnConnectionToPool(Connection connection) {
//        connectionFactory.returnConnectionToPool(connection);
//    }

    protected void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected String getSelectAllFieldsTemplate(){
        return SELECT_QUERY + getTableName();
    }

    public void deleteById(long id) throws SQLException {
        String sqlQuery = "DELETE FROM " + getTableName() + " WHERE id=?";

        PreparedStatement statement = getPreparedStatement(sqlQuery);
        statement.setLong(1, id);
        System.out.println(statement + " id=" + id);
        statement.executeUpdate();
    }

    public List<T> selectAll() throws SQLException, NoSuchFieldException, IllegalAccessException {
        String sqlQuery = getSelectAllFieldsTemplate();
        PreparedStatement statement = getPreparedStatement(sqlQuery);
        System.out.println(sqlQuery);
        return getListEntitiesFromPreparedStatement(statement);
    }

    public T selectById(long id) throws SQLException, IllegalAccessException, NoSuchFieldException {
        String sqlQuery = getSelectAllFieldsTemplate() + " WHERE id = ?";
        PreparedStatement statement = getPreparedStatement(sqlQuery);
        statement.setLong(1, id);
        System.out.println(sqlQuery + " id=" + id);

        try{
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return getEntityFromResultSet(resultSet);
            }
        }
        finally {
            closePrepareStatement(statement);
        }
        return null;
    }


    protected List<T> getListEntitiesFromPreparedStatement(PreparedStatement statement){
        List<T> entities = getEmptyEntityList();
        try{
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                entities.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(statement);
        }
        return entities;
    }

    protected List<T> getEmptyEntityList(){
        return new ArrayList<>();
    }


    protected void executePreparedStatement(PreparedStatement statement) throws SQLException {
        System.out.println(statement);
        try{
            statement.execute();
        }
        finally {
            closePrepareStatement(statement);
        }
    }

    protected abstract T getEntityFromResultSet(ResultSet resultSet) throws SQLException;
    protected abstract String getTableName();
}
