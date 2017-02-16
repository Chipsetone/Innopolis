package com.semakin.jdbc.entitylogic;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public abstract class EntityRepository<T extends Entity>  implements IEntityQueryable<T>{
    protected Connection connection = ConnectionFactory.getInstance().getConnection();
    private String tableName;
    private Class entityClass;

    public EntityRepository(Class entityClass) {
        this.entityClass = entityClass;
        this.tableName = entityClass.getSimpleName();
    }

    protected String getInsertQueryString(String fieldNames, String values){
        return "INSERT INTO " + tableName + " " + fieldNames + "VALUES" + values;
    }

    protected String getSelectAllQueryString(){
        return "SELECT * FROM " + tableName;
    }

    protected String getDeleteByIdQueryString(long id){
        return "DELETE FROM " + tableName + " WHERE id = " + id;
    }

    protected String getSelectByIdQueryString(long id){
        return "SELECT TOP 1 * FROM " + tableName + " where id = " + id;
    }

    private static void insertDb() throws SQLException {
        Connection conn = ConnectionFactory.getInstance().getConnection();
        String sqlQuery = "INSERT INTO student (name, birthdate, sex)" + //, id_group)" +
                "VALUES(?,?,?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        preparedStatement.setString(1, "Arnold");
        preparedStatement.setDate(2, new Date(1978, 2,5));
        preparedStatement.setBoolean(3, true);
        //preparedStatement.setInt(4, 1);
        preparedStatement.executeUpdate();
    }

//    public void insert(T entity) throws SQLException, IllegalAccessException {
//        insertObjectIntoDb(entity);
//    }
//
//    public T selectById(long id){
//
//    }
//
//    public List<T> selectAll() {
//
//    }
//
//    public void update(T entity){
//        // полный запрос на update
//        // по равенству ключа
//    }
//
//    public void deleteById(long id){
//        // получить id
//        // отправить запрос на удаление
//
//    }

//    private void insertObjectIntoDb(T entity) throws IllegalAccessException, SQLException {
//        String query = "";
//        String fieldNames = "(";
//        String fieldValues = "(";
//        //Class entityClass = entity.getClass();
//        for (Field field :
//                entityClass.getDeclaredFields()) {
//            field.setAccessible(true);
//            Object value = field.get(entity);
//            if (value == null) {
//                continue;
//            }
//            else if(shouldShield(field)){
//                value = "'" + value + "'";
//            }
//            String name = field.getName();
//            if(name == "id") {
//                continue;
//            }
//            fieldNames += name + ",";
//            fieldValues += value + ",";
//            System.out.println("names: " + fieldNames);
//
//        }
//        int namesLength = fieldNames.length();
//        int valuesLength = fieldValues.length();
//
//        if(namesLength > 1 && valuesLength > 1){
//            fieldNames = fieldNames.substring(0, fieldNames.length() - 1).concat(")");
//            fieldValues = fieldValues.substring(0, fieldValues.length() - 1).concat(")");
//        }
//
//        String tableName = entityClass.getSimpleName();
//        String insertQuery = getInsertQueryString(tableName, fieldNames, fieldValues);
//        System.out.println(insertQuery);
//
//        executeUpdate(insertQuery);
//    }
//
//    private boolean shouldShield(Field field){
//        String simpleTypeName =  field.getType().getSimpleName();
//        if(simpleTypeName.equals("String") || simpleTypeName.equals("Date")){
//            return true;
//        }
//        return false;
//    }

//    protected void executeUpdate(String sqlQuery) throws SQLException {
//        Statement statement = connection.createStatement();
//
//        statement.executeUpdate(sqlQuery);
//    }
}
