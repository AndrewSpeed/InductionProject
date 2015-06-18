import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	
	static Connection conn;
	static Statement stmt;
	public static void main(String[] args){
		
		String url = "jdbc:mysql://localhost:3306/project_employees";
		
		
		//load the JDBC driver
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(ClassNotFoundException e){
			System.err.print(e.getMessage());
		}
		
		//setting up connection
		try{
			
			conn = DriverManager.getConnection(url, "root", "password");		
		
		}
		catch(SQLException e){
			System.err.print(e.getMessage());
		}
		
		
		try{
		
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from employees");
		
		while(rs.next()){
			
            int customerID = rs.getInt("id");
            String fname = rs.getString("forename");
            String lname = rs.getString("surname");
            int salaryID = rs.getInt("salaryid");
            System.out.println(customerID + "\t" + fname +
                               "\t" + lname + "\t" + salaryID);	
		}
		}
		catch(SQLException e){
			System.err.print(e.getMessage());			
		}
		
		
		
		}//psvm
	
}//class