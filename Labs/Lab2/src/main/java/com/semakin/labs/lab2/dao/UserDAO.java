package com.semakin.labs.lab2.dao;

import com.semakin.labs.lab2.db.AbstractDAO;
import com.semakin.labs.lab2.db.ConnectionFactory;
import com.semakin.labs.lab2.db.IEntityQueryable;
import com.semakin.labs.lab2.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Семакин Виктор
 */
public class UserDAO extends AbstractDAO<User> implements IEntityQueryable<User> {
    private static final String SELECT_QUERY = "SELECT * FROM USER";

    public UserDAO(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    public User selectById(long id) throws SQLException, IllegalAccessException, NoSuchFieldException {
        String sqlQuery = SELECT_QUERY + " WHERE id = ?";
        PreparedStatement statement = getPreparedStatement(sqlQuery);
        statement.setLong(1, id);

        try{
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return getUserFromResultSet(resultSet);
            }
        }
        finally {
            closePrepareStatement(statement);
        }
        return null;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
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
    private List<User> getUserListFromPreparedStatement(PreparedStatement statement){
        List<User> users = new ArrayList<User>();
        try{
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(statement);
        }
        return users;
    }

    public List<User> selectAll() throws SQLException, NoSuchFieldException, IllegalAccessException {
        String sqlQuery = SELECT_QUERY;
        PreparedStatement statement = getPreparedStatement(sqlQuery);

        return getUserListFromPreparedStatement(statement);
    }

    public void insert(User user) throws SQLException, IllegalAccessException {
        final String INSERT_QUERY_NAMES = "bitrix_id,firstName,middleName,lastName, email, phone, birthDate";
        final String INSERT_QUERY = "INSERT INTO stc.public.user("+ INSERT_QUERY_NAMES + ") VALUES(?,?,?,?,?,?,?)";

        PreparedStatement statement = getPreparedStatement(INSERT_QUERY);

        statement.setLong(1, user.getBitrix_id());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getMiddleName());
        statement.setString(4, user.getLastName());
        statement.setString(5, user.getEmail());
        statement.setString(6, user.getPhone());
        statement.setDate(7, user.getBirthDate());
        System.out.println(statement);
        try{
            statement.execute();
        }
        finally {
            closePrepareStatement(statement);
        }
    }

    public void update(User user) throws SQLException, IllegalAccessException {
        final String UPDATE_VALUE_PAIRS = "bitrix_id =?,firstName =?, middleName =?,lastName =?, email =?, phone =?, birthDate =?";
        String sqlQuery = "UPDATE USER SET " + UPDATE_VALUE_PAIRS + " WHERE id = ?";

        PreparedStatement statement = getPreparedStatement(sqlQuery);
        statement.setLong(1, user.getBitrix_id());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getMiddleName());
        statement.setString(4, user.getLastName());
        statement.setString(5, user.getEmail());
        statement.setDate(6, user.getBirthDate());
        statement.setLong(7,user.getId());

        try{
            statement.execute();
        }
        finally {
            closePrepareStatement(statement);
        }
    }

    public void deleteById(long id) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
