import java.util.List;

public class GeoJsonConverter {

    public JSONArray convertToGeoJson(List<Sighting> sightings) {
        JSONArray markers = new JSONArray();

        for (Sighting sighting : sightings) {
            JSONObject marker = new JSONObject();
            marker.put("type", "Feature");

            // Add coordinates
            JSONObject geometry = new JSONObject();
            geometry.put("type", "Point");
            geometry.put("coordinates", new JSONArray(new double[]{sighting.getLongitude(), sighting.getLatitude()}));
            marker.put("geometry", geometry);

            // Add properties including date and timestamp
            JSONObject properties = new JSONObject();
            properties.put("date", sighting.getDate());
            properties.put("timestamp", sighting.getTimestamp().toString());
            marker.put("properties", properties);

            markers.put(marker);
        }

        return markers;
    }
}
