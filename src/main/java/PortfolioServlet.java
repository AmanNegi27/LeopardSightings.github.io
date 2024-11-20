import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/PortfolioServlet")
public class PortfolioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/leopard", "root", "negs27@sql")) {
           
            String query = "SELECT longitude, latitude, timestamp, date, image, accuracy FROM collection";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            
            List<Map<String, Object>> dataList = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> data = new HashMap<>();
                data.put("longitude", rs.getString("longitude"));
                data.put("latitude", rs.getString("latitude"));
                data.put("timestamp", rs.getString("timestamp"));
                data.put("date", rs.getString("date"));
                data.put("image", rs.getString("image"));  
                data.put("accuracy", rs.getString("accuracy"));
                dataList.add(data);
            }

           
            request.setAttribute("portfolioData", dataList);

            
            request.getRequestDispatcher("portfolio.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
