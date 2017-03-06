package students;

import org.springframework.beans.factory.annotation.Autowired;
import students.common.exceptions.UserDAOException;
import students.models.dao.UserDAO;

public class Test {
    @Autowired
    private static UserDAO userDAO;

    public static void main(String[] args) {



//        Connection con = AcademConnector.getConnection();
        try {
            userDAO.registrationUser("loginww", "1","wwww");
        } catch (UserDAOException e) {
            e.printStackTrace();
        }
//
//        try {
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM \"Main\".\"User\"");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
