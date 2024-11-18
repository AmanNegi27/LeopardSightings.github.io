import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortfolioService {

    // Method to get user sightings based on username
    public List<Map<String, Object>> getUserSightings(String username) {
        List<Map<String, Object>> sightings = new ArrayList<>();
        String query = "SELECT latitude, longitude, date, timestamp, image, accuracy FROM collection WHERE username = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leopard", "root", "negs27@sql");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the username parameter in the query
            stmt.setString(1, username);
            
            // Execute the query
            ResultSet rs = stmt.executeQuery();
            
            // Iterate over the result set and map each row to a Map
            while (rs.next()) {
                Map<String, Object> sightingData = new HashMap<>();
                
                // Put the results into the map with column names as keys
                sightingData.put("latitude", rs.getDouble("latitude"));
                sightingData.put("longitude", rs.getDouble("longitude"));
                sightingData.put("date", rs.getString("date"));
                sightingData.put("timestamp", rs.getTimestamp("timestamp"));
                sightingData.put("image", rs.getBlob("image"));
                sightingData.put("accuracy", rs.getInt("accuracy"));
                
                // Add the map to the sightings list
                sightings.add(sightingData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Return the list of sightings (as List<Map<String, Object>>)
        return sightings;
    }
}
