import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the image ID from the request (assuming each image has a unique ID)
        String imageId = request.getParameter("imageId");

        if (imageId != null) {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/leopard", "root", "negs27@sql");

                // Query to fetch the image BLOB by image ID
                String query = "SELECT image FROM collection WHERE id = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, imageId);
                rs = ps.executeQuery();

                if (rs.next()) {
                    // Set the response content type (assuming the image is stored as JPEG)
                    response.setContentType("image/jpeg");

                    // Get the image as a byte array
                    InputStream imageStream = rs.getBinaryStream("image");

                    // Write the image to the response output stream
                    ServletOutputStream outStream = response.getOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = imageStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }

                    imageStream.close();
                    outStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Close resources
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Image ID is required");
        }
    }
}
