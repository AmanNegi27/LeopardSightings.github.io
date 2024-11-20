import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
          
            Class.forName("com.mysql.cj.jdbc.Driver");

           
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/leopard", "root", "negs27@sql");

           
            String username = request.getParameter("username");
            String password = request.getParameter("password");

           
            String query = "SELECT userName FROM member WHERE userName = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

          
            ResultSet rs = ps.executeQuery();

            
            if (rs.next()) {
               
                request.getSession().setAttribute("username", username);
                
                
                response.sendRedirect("dashboard.html?username=" + username);
            } else {
                
                request.setAttribute("errorMessage", "Login failed: Username or password incorrect.");
                
               
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

            // Close resources
            rs.close();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
