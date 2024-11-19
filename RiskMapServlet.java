import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RiskMapServlet")
public class RiskMapServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/leopard", "root", "negs27@sql");

            // Query to fetch distinct latitude, longitude, date, timestamp, and image ID
            String query = "SELECT DISTINCT latitude, longitude, date, timestamp, id FROM collection WHERE date >= DATE_SUB(CURDATE(), INTERVAL 10 DAY)";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            // Store fetched data in a list of LinkedHashMaps
            List<Map<String, Object>> locationData = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> location = new LinkedHashMap<>(); // Maintain insertion order

                // Add data in the desired order
                location.put("latitude", rs.getString("latitude"));
                location.put("longitude", rs.getString("longitude"));
                location.put("date", rs.getDate("date").toString()); // Convert to String
                location.put("timestamp", rs.getTime("timestamp").toString()); // Convert to String
                location.put("imageId", rs.getString("id")); // Add image ID for the BLOB

                locationData.add(location);
            }

            // Convert list to JSON and send as a response
            String json = new Gson().toJson(locationData);
            out.print(json);

            // Close resources
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.print("[]"); // Return empty JSON array on error
        } finally {
            AbandonedConnectionCleanupThread.uncheckedShutdown();

            // Close output stream
            out.close();
        }
    }
}
