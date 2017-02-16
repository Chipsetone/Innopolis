import java.sql.*;

/**
 * @author Семакин Виктор
 */

public class main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        //String url = "http:\\\\localhost:5433";
        String url2 = "jdbc:postgresql://localhost:5433/students";

        try(Connection conn = DriverManager.getConnection(url2, "postgres", "admin")){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

            while(resultSet.next()){
                String readedName = resultSet.getString("name");
                System.out.println(readedName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertDb(String url, String login, String password){
        try(Connection conn = DriverManager.getConnection(url, login, password)) {
            String sqlQuery = "INSERT INTO students.student (name, birthdate, sex, group_id)" +
                    "VALUES(?,?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, "Arnold");
            preparedStatement.setDate(2, new Date(1978, 2,5));
            preparedStatement.setBoolean(3, true);
            preparedStatement.setInt(4, 1);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
