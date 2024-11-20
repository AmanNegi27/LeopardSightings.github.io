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
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) 
public class DataCollectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    String dbUrl = "jdbc:mysql://localhost:3306/leopard";
    String dbUser = "root";
    String dbPassword = "negs27@sql";

   
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

       
        Part imagePart = request.getPart("image");
        String date = request.getParameter("date");
        String timestamp = request.getParameter("timestamp");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String accuracy = request.getParameter("accuracy");
        String username = request.getParameter("username");

        
        if (username != null) username = username.trim();

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
          
            String checkUserQuery = "SELECT COUNT(*) FROM member WHERE username = ?";
            PreparedStatement checkUserStmt = connection.prepareStatement(checkUserQuery);
            checkUserStmt.setString(1, username);

            ResultSet rs = checkUserStmt.executeQuery();
            rs.next();
            int userCount = rs.getInt(1);

            if (userCount == 0) {
               
                response.getWriter().println("Error: Username does not exist in the database.");
                return;
            }

            
            String sql = "INSERT INTO Collection (username, image, date, timestamp, latitude, longitude, accuracy) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

           
            if (imagePart != null && imagePart.getSize() > 0) {
                InputStream imageInputStream = imagePart.getInputStream();
                statement.setBlob(2, imageInputStream); 
            } else {
                statement.setNull(2, java.sql.Types.BLOB); 
            }

          
            statement.setString(1, username); 
            statement.setString(3, date);
            statement.setString(4, timestamp);
            statement.setDouble(5, Double.parseDouble(latitude)); 
            statement.setDouble(6, Double.parseDouble(longitude)); 
            statement.setString(7, accuracy); 

            
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
