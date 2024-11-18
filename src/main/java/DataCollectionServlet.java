import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/DataCollectionServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // Limit file size to 5MB
public class DataCollectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database credentials
    String dbUrl = "jdbc:mysql://localhost:3306/leopard";
    String dbUser = "root";
    String dbPassword = "negs27@sql";

    // Register MySQL driver explicitly (Optional)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve data from the form
        Part imagePart = request.getPart("image");
        String date = request.getParameter("date");
        String timestamp = request.getParameter("timestamp");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String accuracy = request.getParameter("accuracy");
        String username = request.getParameter("username"); // Username field

        // Handle null or empty inputs (trim any extra spaces)
        if (username != null) username = username.trim();

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            // Check if the username exists in the database
            String checkUserQuery = "SELECT COUNT(*) FROM member WHERE username = ?";
            PreparedStatement checkUserStmt = connection.prepareStatement(checkUserQuery);
            checkUserStmt.setString(1, username);

            ResultSet rs = checkUserStmt.executeQuery();
            rs.next();
            int userCount = rs.getInt(1);

            if (userCount == 0) {
                // Username does not exist in the database
                response.getWriter().println("Error: Username does not exist in the database.");
                return;
            }

            // If username exists, proceed with the data insertion
            String sql = "INSERT INTO Collection (username, image, date, timestamp, latitude, longitude, accuracy) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set image data as binary stream, if present
            if (imagePart != null && imagePart.getSize() > 0) {
                InputStream imageInputStream = imagePart.getInputStream();
                statement.setBlob(2, imageInputStream); // Set image
            } else {
                statement.setNull(2, java.sql.Types.BLOB); // Handle no image case
            }

            // Set other data fields
            statement.setString(1, username); // Set the username
            statement.setString(3, date);
            statement.setString(4, timestamp);
            statement.setDouble(5, Double.parseDouble(latitude)); // Set latitude
            statement.setDouble(6, Double.parseDouble(longitude)); // Set longitude
            statement.setString(7, accuracy); // Set accuracy

            // Execute the insert statement
            int row = statement.executeUpdate();
            if (row > 0) {
                response.getWriter().println("Data uploaded and saved successfully.");
                response.sendRedirect("dashboard.html?username=" + username);
            } else {
                response.getWriter().println("Failed to upload data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database error: " + e.getMessage());
        }
    }
}
