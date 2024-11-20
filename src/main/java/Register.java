import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String mobileNumber = request.getParameter("mobileNumber");
        String address = request.getParameter("address");
        String dateOfRegisteration = request.getParameter("dateOfRegisteration");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        
        Members member = new Members(name, mobileNumber, address, dateOfRegisteration, password, userName);
        DatabaseManager mg = new DatabaseManager();
        String result = mg.insert(member);
        
        response.getWriter().print(result);
        if ("Data Entered Successfully".equals(result)) {
            
            response.sendRedirect("login.jsp");
        } else {
           
            response.getWriter().print("Data insertion failed. Please try again.");
        }
    }
}
