package com.semakin.labs.lab2.dao;

import com.semakin.labs.lab2.connection.IConnectionFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public abstract class AbstractDAO<T> implements IEntityQueryable<T> {
    private static Logger logger = Logger.getLogger(AbstractDAO.class);

    private final Connection connection;
    private static final String SELECT_QUERY = "SELECT * FROM ";
    private IConnectionFactory connectionFactory;

    AbstractDAO(IConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        this.connection = connectionFactory.getConnection();
    }

    PreparedStatement getPreparedStatement(String sqlQuery){
        try {
            return connection.prepareStatement(sqlQuery);
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }

    // TODO использовать
//    public void returnConnectionToPool(Connection connection) {
//        connectionFactory.returnConnectionToPool(connection);
//    }

    private void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String getSelectAllFieldsTemplate(){
        return SELECT_QUERY + getTableName();
    }

    public void deleteById(long id) throws SQLException {
        String sqlQuery = "DELETE FROM " + getTableName() + " WHERE id=?";

        PreparedStatement statement = getPreparedStatement(sqlQuery);
        statement.setLong(1, id);
        logger.info(statement + " id=" + id);
        statement.executeUpdate();
    }

    @Override
    public void deleteAll() {
        String sqlQuery = "DELETE FROM " + getTableName();

        try(Connection conn = getConnectionFactory().getConnection();
            PreparedStatement statement = conn.prepareStatement(sqlQuery)) {

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public List<T> selectAll() {
        String sqlQuery = getSelectAllFieldsTemplate();
        PreparedStatement statement = getPreparedStatement(sqlQuery);
        System.out.println(sqlQuery);
        return getListEntitiesFromPreparedStatement(statement);
    }

    public T selectById(long id) {
        String sqlQuery = getSelectAllFieldsTemplate() + " WHERE id = ?";
        PreparedStatement statement = getPreparedStatement(sqlQuery);
        try {
            statement.setLong(1, id);
            logger.info(sqlQuery + " id=" + id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return getEntityFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            closePrepareStatement(statement);
        }
        return null;
    }


    private List<T> getListEntitiesFromPreparedStatement(PreparedStatement statement){
        List<T> entities = getEmptyEntityList();
        try{
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                entities.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            closePrepareStatement(statement);
        }
        return entities;
    }

    private List<T> getEmptyEntityList(){
        return new ArrayList<>();
    }


    protected void executePreparedStatement(PreparedStatement statement) throws SQLException {
        logger.info(statement);
        try{
            statement.execute();
        }
        finally {
            closePrepareStatement(statement);
        }
    }

    public IConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    protected abstract T getEntityFromResultSet(ResultSet resultSet) throws SQLException;
    protected abstract String getTableName();
}
