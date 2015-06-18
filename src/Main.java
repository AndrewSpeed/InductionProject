import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	
	static Connection conn;
	static Statement stmt;
	static String url = "jdbc:mysql://localhost:3306/project_employees";
	
	
	public static void main(String[] args){	
		
		
		//load the JDBC driver		
		loadJDBCdriver();
		
		//setting up connection
		setUpConnection();
		
		
		try{
		
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(selectQuery(1));
		
		//ArrayList results = new ArrayList(rs);
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
	
	
	/*
	 * selectQuery method to run select query using different userID's
	 */
	public static String selectQuery(int userID){
		String queryString = ("Select * from employees WHERE id =" + userID);
		return queryString;
	}
	
	
	/*
	 * loadJDBCdriver method loads the JDBC driver
	 */
	public static void loadJDBCdriver(){
		//load the JDBC driver
				try{
					Class.forName("com.mysql.jdbc.Driver");
					
				}catch(ClassNotFoundException e){
					System.err.print(e.getMessage());
				}	
	}
	
	/*
	 * setUpConnection method sets up the connection to the database via a URL
	 */
	public static void setUpConnection(){
		//setting up connection
		try{
					
			conn = DriverManager.getConnection(url, "root", "password");		
				
			}
		catch(SQLException e){
					System.err.print(e.getMessage());
			}
	}
	
}//class