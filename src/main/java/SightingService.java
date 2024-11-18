import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SightingService {

    public List<Sighting> getSightings() {
        List<Sighting> sightings = new ArrayList<>();
        
        // Modify the query to get records from the last month only
        String query = "SELECT latitude, longitude, date, timestamp FROM collection WHERE date >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leopard", "root", "negs27@sql");
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                String date = rs.getString("date");
                Timestamp timestamp = rs.getTimestamp("timestamp");

                sightings.add(new Sighting(latitude, longitude, date, timestamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sightings;
    }
}
