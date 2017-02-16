package com.semakin.jdbc.entitylogic;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author Семакин Виктор
 */

public class SQLQueryFactory {
    private Connection connection = ConnectionFactory.getInstance().getConnection();

    public void InsertObjectIntoDb(Object entity) throws IllegalAccessException, SQLException {
        String query = "";
        String fieldNames = "(";
        String fieldValues = "(";
        Class studentClass = entity.getClass();
        for (Field field :
                studentClass.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(entity);
            if (value == null) {
                continue;
            }
            else if(shouldShield(field)){
                value = "'" + value + "'";
            }
            String name = field.getName();
            if(name == "id") {
                continue;
            }
            fieldNames += name + ",";
            fieldValues += value + ",";
            System.out.println("names: " + fieldNames);

        }
        int namesLength = fieldNames.length();
        int valuesLength = fieldValues.length();

        if(namesLength > 1 && valuesLength > 1){
            fieldNames = fieldNames.substring(0, fieldNames.length() - 1).concat(")");
            fieldValues = fieldValues.substring(0, fieldValues.length() - 1).concat(")");
        }

        String tableName = studentClass.getSimpleName();
        String insertQuery = MakeInsertQuery(tableName, fieldNames, fieldValues);
        System.out.println(insertQuery);

        executeSQL(insertQuery);
    }

    private boolean shouldShield(Field field){
        String simpleTypeName =  field.getType().getSimpleName();
        if(simpleTypeName.equals("String") || simpleTypeName.equals("Date")){
            return true;
        }

        return false;
    }

    private String MakeInsertQuery(String tableName, String fieldNames, String values){
        return "INSERT INTO " + tableName + " " + fieldNames + "VALUES" + values;
    }

    private void executeSQL(String sqlQuery) throws SQLException {
        Statement statement = connection.createStatement();

        statement.executeUpdate(sqlQuery);
    }
}
