import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	
	public static void main(String[] args){
		
		String url = "jdbc:mysql://localhost:3306/project_employees";
		Connection conn;
		Statement stmt;
		
		//load the JDBC driver
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(ClassNotFoundException e){
			System.err.print(e.getMessage());
		}
		
		//setting up connection
		try{
			
			conn = DriverManager.getConnection(url, "root", "password");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from employees");
				
		}
		catch(SQLException e){
			System.err.print(e.getMessage());
		}
		
		
		
		}//psvm
	
}//class