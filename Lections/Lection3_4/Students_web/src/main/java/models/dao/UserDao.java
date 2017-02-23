package models.dao;

import models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * @author Семакин Виктор
 */
public class UserDao extends AbstractDAO<User> {
    private static Logger logger = Logger.getLogger(UserDao.class);
    private IConnectionFactory connectionFactory;

    public UserDao() {
        //super(ConnectionFactory.getInstance());
        this.connectionFactory = ConnectionFactory.getInstance();
    }

    public void insert(User entity) throws SQLException {

    }

    protected User getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setIduser(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getString("role"));

        return user;
    }


    public User getUserByLoginAndPassword(String login, String password){
        String sqlQuery =  SELECT_QUERY + getTableName() + " WHERE login=? AND password=?";

        try(Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            logger.info("соединение создано");
            statement.setString(1, login);
            statement.setString(2, password);
            logger.info("запрос сформирован " + statement.toString());
            statement.execute();
            logger.info("запрос отправлен");
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()){
                logger.info("данные получены");
                return getEntityFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("ошибка при чтении из таблицы user", e);
        }
        return null;
    }

    public boolean registrationUser(String login, String password, String role){
        final String sqlQuery = "INSERT INTO " + getTableName() + "(login, password, role) VALUES(?,?,?)";

        try(Connection connection = connectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, login);
            statement.setString(1, password);
            statement.setString(1, role);

            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {

            logger.error("ошибка при вставке ", e);
        }

        return false;
    }

    protected String getTableName() {
        return "students.public.user";
    }
}
