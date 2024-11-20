import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginManager {

    private Connection connection;

    public LoginManager(Connection connection) {
        this.connection = connection;
    }

    public boolean validateUser(String username, String password) {
        String sql = "SELECT * FROM members WHERE userName = ? AND password = ?"; 

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
