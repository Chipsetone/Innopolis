package com.semakin.labs.lab2.dao;

import com.semakin.labs.lab2.db.IConnectionFactory;
import com.semakin.labs.lab2.entitiessimple.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Семакин Виктор
 */
public class UserDAO extends AbstractDAO<User> implements IEntityQueryable<User> {
    private static final String TABLE_NAME = "stc.public.user";

    public UserDAO(IConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    protected User getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setBitrix_id(resultSet.getLong("bitrix_id"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setMiddleName(resultSet.getString("middlename"));
        user.setLastName(resultSet.getString("lastname"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setBirthDate(resultSet.getDate("birthdate"));

        return user;
    }

    public void insert(User user) throws SQLException {
        final String INSERT_QUERY_NAMES = "bitrix_id,firstName,middleName,lastName, email, phone, birthDate";
        final String INSERT_QUERY = "INSERT INTO " + getTableName() + "("+ INSERT_QUERY_NAMES + ") VALUES(?,?,?,?,?,?,?)";

        PreparedStatement statement = getPreparedStatement(INSERT_QUERY);

        statement.setLong(1, user.getBitrix_id());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getMiddleName());
        statement.setString(4, user.getLastName());
        statement.setString(5, user.getEmail());
        statement.setString(6, user.getPhone());
        statement.setDate(7, user.getBirthDate());

        executePreparedStatement(statement);
    }

    protected String getTableName() {
        return TABLE_NAME;
    }

}
