package com.semakin.labs.lab2.dao;

import com.semakin.labs.lab2.db.AbstractDAO;
import com.semakin.labs.lab2.db.ConnectionFactory;
import com.semakin.labs.lab2.db.IEntityQueryable;
import com.semakin.labs.lab2.entities.Interview;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Семакин Виктор
 */
public class InterviewDAO extends AbstractDAO<Interview> implements IEntityQueryable<Interview> {
    private static final String TABLE_NAME = "stc.public.interview";

    public InterviewDAO(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void insert(Interview interview) throws SQLException, IllegalAccessException {
        final String INSERT_QUERY_NAMES = "name";
        final String INSERT_QUERY = "INSERT INTO " + getTableName() + "("+ INSERT_QUERY_NAMES + ") VALUES(?)";

        PreparedStatement statement = getPreparedStatement(INSERT_QUERY);
        statement.setString(1, interview.getName());

        executePreparedStatement(statement);
    }

    @Override
    protected Interview getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Interview interview = new Interview();

        interview.setId(resultSet.getLong("id"));
        interview.setName(resultSet.getString("name"));

        return interview;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }
}
