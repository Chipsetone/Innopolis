package com.semakin.jdbc.entitylogic;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Семакин Виктор
 */

public class ResultSetConverter<T extends Entity> {
    private Class entityClass;

    public ResultSetConverter(Class entityClass) {
        this.entityClass = entityClass;
    }

    public T getEntityFromResultSet(ResultSet resultSet) throws SQLException, IllegalAccessException, NoSuchFieldException {
        T result;
        try {
            result = (T) entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        fillFields(result, resultSet);
        return result;
    }

    private void fillFields(T result, ResultSet resultSet) throws NoSuchFieldException, SQLException, IllegalAccessException {
        // fill parent class id field
        final String idFieldName = "id";
        Class parentClass = entityClass.getSuperclass();
        Field fieldId = parentClass.getDeclaredField(idFieldName);
        long id = resultSet.getLong(idFieldName);
        fieldId.setLong(result, id);

        // fill class fields
        for (Field field : entityClass.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = getFieldValueFromResultSet(resultSet, field, fieldName);
            field.set(result, fieldValue);
        }
    }

    private Object getFieldValueFromResultSet(ResultSet source, Field fieldTarget, String fieldName) throws SQLException, IllegalAccessException {
        Class fieldClass = fieldTarget.getType();
        if(fieldClass.equals(Character.class)){
            return source.getString(fieldName).charAt(0); // ResultSet не умеет char! =(
        }
        else if(fieldClass.equals(Date.class)){
            return source.getDate(fieldName);
        }

        return source.getObject(fieldName);
    }

}
