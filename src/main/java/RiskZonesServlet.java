import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RiskZonesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SightingService sightingService = new SightingService();
        GeoJsonConverter geoJsonConverter = new GeoJsonConverter();

        List<Sighting> sightings = sightingService.getSightings();
        JSONArray geoJsonData = (JSONArray) geoJsonConverter.convertToGeoJson(sightings);

        response.setContentType("application/json");
        response.getWriter().print(geoJsonData.toString());
    }
}
