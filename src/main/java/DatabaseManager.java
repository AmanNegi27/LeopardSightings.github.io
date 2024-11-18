import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DatabaseManager {
	private String dburl="jdbc:mysql://localhost:3306/leopard";
	private String dbuname="root";
	private String dbpassword="negs27@sql";
	private String dbdriver="com.mysql.cj.jdbc.Driver";
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbdriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection()
	{
		Connection con=null;
		try {
			
		con= DriverManager.getConnection(dburl,dbuname,dbpassword);
		con.commit();

		
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return con;
	}
	public String insert(Members member) {
	    loadDriver(dbdriver);
	    Connection con = getConnection();
	    String result = "Data Entered Successfully";
	    String sql = "INSERT INTO leopard.member VALUES(?,?,?,?,?,?)";
	    
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, member.getName());
	        ps.setString(2, member.getMobileNumber());
	        ps.setString(3, member.getAddress());
	        ps.setString(4, member.getDateOfRegisteration());
	        ps.setString(5, member.getPassword());
	        ps.setString(6, member.getUserName());
	        
	        // Execute update
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        result = "Data Not Entered";
	    } finally {
	        try {
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return result;
	}
}
