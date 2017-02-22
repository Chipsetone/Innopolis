package com.semakin.labs.lab2.dao;

import com.semakin.labs.lab2.db.IConnectionFactory;
import com.semakin.labs.lab2.entities.Superuser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Семакин Виктор
 */
public class SuperuserDAO extends AbstractDAO<Superuser> implements IEntityQueryable<Superuser>{
    private static final String TABLE_NAME = "stc.public.superuser";

    public SuperuserDAO(IConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Superuser getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Superuser superuser = new Superuser();

        superuser.setId(resultSet.getLong("id"));
        superuser.setFirstName(resultSet.getString("firstname"));
        superuser.setLastName(resultSet.getString("lastname"));
        superuser.setMiddleName("middlename");
        superuser.setEmail(resultSet.getString("email"));

        return superuser;
    }

    @Override
    public void insert(Superuser superuser) throws SQLException {
        final String INSERT_QUERY_NAMES = "firstName,middleName,lastName, email";
        final String INSERT_QUERY = "INSERT INTO " + getTableName() + "("+ INSERT_QUERY_NAMES + ") VALUES(?,?,?,?)";

        PreparedStatement statement = getPreparedStatement(INSERT_QUERY);
        statement.setString(1, superuser.getFirstName());
        statement.setString(2, superuser.getMiddleName());
        statement.setString(3, superuser.getLastName());
        statement.setString(4, superuser.getEmail());

        executePreparedStatement(statement);
    }
}
