package com.semakin.labs.lab2.dao;

import com.semakin.labs.lab2.db.AbstractDAO;
import com.semakin.labs.lab2.db.ConnectionFactory;
import com.semakin.labs.lab2.db.IEntityQueryable;
import com.semakin.labs.lab2.entities.InterviewResult;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Семакин Виктор
 */
public class InterviewResultDAO extends AbstractDAO<InterviewResult> implements IEntityQueryable<InterviewResult> {
    private static final String TABLE_NAME = "stc.public.interview_result";

    public InterviewResultDAO(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void insert(InterviewResult interviewResult) throws SQLException, IllegalAccessException {
        final String INSERT_QUERY_NAMES = "user_id, superuser_id, created_at, total_rating";
        final String INSERT_QUERY = "INSERT INTO " + getTableName() + "("+ INSERT_QUERY_NAMES + ") VALUES(?,?,?,?)";

        PreparedStatement statement = getPreparedStatement(INSERT_QUERY);
        statement.setLong(1, interviewResult.getUserId());
        statement.setLong(2, interviewResult.getSuperUserId());
        statement.setDate(3, interviewResult.getCreatedAt());
        statement.setShort(4, interviewResult.getTotalRating());

        executePreparedStatement(statement);
    }

    @Override
    protected InterviewResult getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        InterviewResult interviewResult = new InterviewResult();

        interviewResult.setId(resultSet.getLong("id"));
        interviewResult.setUserId(resultSet.getLong("user_id"));
        interviewResult.setSuperUserId(resultSet.getLong("superuser_id"));
        interviewResult.setTotalRating(resultSet.getShort("total_rating"));

        return interviewResult;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }
}
