package com.semakin.jdbc.entitylogic;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public abstract class GodEntityRepository<T extends Entity>  implements IEntityQueryable<T>{
    private static final String selectAllQuery = "SELECT * FROM ";
    private final Connection connection = ConnectionFactory.getInstance().getConnection();

    private String tableName;
    private ResultSetConverter<T> toEntityConverter;

    public GodEntityRepository(Class entityClass) {
        this.tableName = entityClass.getSimpleName();
        this.toEntityConverter = new ResultSetConverter<T>(entityClass);
    }

    @Override
    public T selectById(long id) throws SQLException, IllegalAccessException, NoSuchFieldException {
        final String sqlQuery = selectAllQuery + tableName + " WHERE id=?";
        PreparedStatement statement = getPreparedStatement(sqlQuery);//conn.prepareStatement(sqlQuery);
        statement.setLong(1, id);

        statement.executeQuery();

        ResultSet resultSet = statement.getResultSet();

        if(resultSet.next()){
            return getEntityFromResultSet(resultSet);
        }
        return null;
    }

    public List<T> selectByName(String name) throws SQLException, NoSuchFieldException, IllegalAccessException {
        final String sqlQuery = selectAllQuery + tableName + " WHERE name=?";
        PreparedStatement statement = getPreparedStatement(sqlQuery);
        statement.setString(1, name);
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();

        return getListFromResultSet(resultSet);
    }
    @Override
    public List<T> selectAll() throws SQLException, NoSuchFieldException, IllegalAccessException {
        final String sqlQuery = selectAllQuery + tableName;
        PreparedStatement statement = getPreparedStatement(sqlQuery);
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();

        return getListFromResultSet(resultSet);
    }

    private List<T> getListFromResultSet(ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {
        List<T> result = new ArrayList<T>();

        while(resultSet.next()){
            T entity = getEntityFromResultSet(resultSet);
            result.add(entity);
        }

        return result;
    }
    @Override
    public void insert(T entity) throws SQLException, IllegalAccessException{
        String fieldNames = "";
        String placesForValues = "";
        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field :
                fields){
            fieldNames +=field.getName() + ",";
            placesForValues += "?,";
        }

        if(fieldNames.length() == 0 || placesForValues.length() == 0){
            return;
        }
        fieldNames = "(" + fieldNames.substring(0, fieldNames.length() - 1) + ")";
        placesForValues = "(" + placesForValues.substring(0, placesForValues.length() - 1) + ")";

        final String insertQuery = "INSERT INTO " + tableName + " " + fieldNames + " VALUES" + placesForValues;

        executeInsert(insertQuery, fields, entity);
    }

    private void executeInsert(String sqlQuery, Field[] fields, T entity) throws SQLException, IllegalAccessException {
        PreparedStatement statement = getPreparedStatement(sqlQuery);
        int i = 1;
        for (Field field:
                fields) {
            field.setAccessible(true);
            prepareValueForStatement(field, statement, field.get(entity), i);
            i++;
        }

        statement.executeUpdate();
    }

    @Override
    public void update(T entity) throws SQLException, IllegalAccessException {
        String columnUpdateMap = "";
        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field :
                fields){
            columnUpdateMap += field.getName() + "=?,";
        }

        if(columnUpdateMap.length() == 0){
            return;
        }
        columnUpdateMap = columnUpdateMap.substring(0, columnUpdateMap.length() - 1);
        final String updateQuery = "UPDATE " + tableName + " SET " + columnUpdateMap + " WHERE id=?";

        executeUpdate(updateQuery, fields, entity);
    }

    private void executeUpdate(String sqlQuery, Field[] fields, T entity) throws SQLException, IllegalAccessException {
        PreparedStatement statement = getPreparedStatement(sqlQuery);
        int i = 1;
        for (Field field:
                fields) {
            field.setAccessible(true);
            prepareValueForStatement(field, statement, field.get(entity), i);
            i++;
        }
        statement.setLong(i, entity.getId());
        System.out.println(statement.toString());
        statement.executeUpdate();
    }

    @Override
    public void delete(T entity) throws SQLException {
        if(entity == null || entity.id == 0){
            return;
        }

        deleteById(entity.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        final String deleteQuery = "DELETE FROM " + tableName + " WHERE id=?";
        PreparedStatement statement = getPreparedStatement(deleteQuery);

        statement.setLong(1, id);
        statement.executeUpdate();
    }

    private T getEntityFromResultSet(ResultSet resultSet) throws SQLException, IllegalAccessException, NoSuchFieldException {
        return toEntityConverter.getEntityFromResultSet(resultSet);
    }

    private void prepareValueForStatement(Field field, PreparedStatement statement, Object fieldValue, int insertedIndex) throws SQLException {
        Class fieldType = field.getType();

        if(fieldType.equals(String.class)){
            statement.setString(insertedIndex, (String)fieldValue);
        } else if (fieldType.equals(int.class)) {
            statement.setInt(insertedIndex, (int)fieldValue);
        } else if (fieldType.equals((long.class))) {
            statement.setLong(insertedIndex, (long)fieldValue);
        } else if (fieldType.equals((Date.class))) {
            statement.setDate(insertedIndex, (Date)fieldValue);
        }
        statement.setObject(insertedIndex, fieldValue);
    }

    private PreparedStatement getPreparedStatement(String sqlQuery) throws SQLException {
        return getConnection().prepareStatement(sqlQuery);
    }


    private Connection getConnection(){
        return connection;
    }
}
