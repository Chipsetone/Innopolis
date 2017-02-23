package com.semakin.labs.lab2.dao;

import com.semakin.labs.lab2.db.IConnectionFactory;
import com.semakin.labs.lab2.entities.InterviewResult;
import com.semakin.labs.lab2.entities.Superuser;
import com.semakin.labs.lab2.entities.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public class InterviewResultDAO extends AbstractDAO<InterviewResult> implements IEntityQueryable<InterviewResult> {
    private static final String TABLE_NAME = "stc.public.interview_result";

    private UserDAO userDAO;
    private SuperuserDAO superuserDAO;

    public InterviewResultDAO(IConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.userDAO = new UserDAO(connectionFactory);
        this.superuserDAO = new SuperuserDAO(connectionFactory);
    }

    @Override
    public List<InterviewResult> selectAll() {
        List<InterviewResult> interviewResults =  super.selectAll();

        IConnectionFactory connectionFactory = getConnectionFactory();
        UserDAO userDAO = new UserDAO(connectionFactory);
        SuperuserDAO superuserDAO = new SuperuserDAO(connectionFactory);

        for (InterviewResult item :
                interviewResults) {
            Long userId = item.getUserId();
            Long superUserId = item.getSuperUserId();

            if (userId != null) {
                User user = userDAO.selectById(userId);
                item.setUser(user);
            }

            if(superUserId != null){
                Superuser superuser = superuserDAO.selectById(superUserId);
                item.setSuperuser(superuser);
            }
        }

        return interviewResults;
    }

    @Override
    public void insert(InterviewResult interviewResult) throws SQLException {
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

        Long userId = interviewResult.getUserId();
        Long superUserId = interviewResult.getSuperUserId();

        if (userId != null) {
            User user = userDAO.selectById(userId);
            interviewResult.setUser(user);
        }

        if(superUserId != null){
            Superuser superuser = superuserDAO.selectById(superUserId);
            interviewResult.setSuperuser(superuser);
        }


        return interviewResult;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }
}
