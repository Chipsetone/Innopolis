package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public abstract class AbstractDAO<T>{//} implements IEntityQueryable<T> {
//    private final Connection connection;
    protected static final String SELECT_QUERY = "SELECT * FROM ";

//    AbstractDAO(IConnectionFactory connectionFactory) {
//        this.connection = connectionFactory.getConnection();
//    }

//    PreparedStatement getPreparedStatement(String sqlQuery){
//        try {
//            return connection.prepareStatement(sqlQuery);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            //TODO прилепить логгер не забыть
//        }
//        return null;
//    }

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

//    public void deleteById(long id) throws SQLException {
//        String sqlQuery = "DELETE FROM " + getTableName() + " WHERE id=?";
//
//        PreparedStatement statement = getPreparedStatement(sqlQuery);
//        statement.setLong(1, id);
//        System.out.println(statement + " id=" + id);
//        statement.executeUpdate();
//    }
//
//    public List<T> selectAll() {
//        String sqlQuery = getSelectAllFieldsTemplate();
//        PreparedStatement statement = getPreparedStatement(sqlQuery);
//        System.out.println(sqlQuery);
//        return getListEntitiesFromPreparedStatement(statement);
//    }
//
//    public T selectById(long id) {
//        String sqlQuery = getSelectAllFieldsTemplate() + " WHERE id = ?";
//        PreparedStatement statement = getPreparedStatement(sqlQuery);
//        try {
//            statement.setLong(1, id);
//            System.out.println(sqlQuery + " id=" + id);
//
//            ResultSet resultSet = statement.executeQuery();
//            if(resultSet.next()) {
//                return getEntityFromResultSet(resultSet);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closePrepareStatement(statement);
//        }
//        return null;
//    }


    private List<T> getListEntitiesFromPreparedStatement(PreparedStatement statement){
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

    private List<T> getEmptyEntityList(){
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
