import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	
	static Connection conn;
	static Statement stmt;
	static String url = "jdbc:mysql://localhost:3306/project_employees";
	
	static Scanner input = new Scanner(System.in);
	
	
	public static void main(String[] args){	
		
		 //Scanner input = new Scanner(System.in);
		
		//load the JDBC driver		
		loadJDBCdriver();
		
		//setting up connection
		setUpConnection();
		
		
		System.out.println("\nWELCOME TO THE EMPLOYEE MANAGEMENT SYSTEM\n*** Main Menu ***\n");
		System.out.println("1. List all employees\n2. Search for employee by ID\n3. Add new employee\n\nPlease type your option number: ");
		
		int option = input.nextInt();
		
		switch (option){
		
		case 1:
			//select all employees
			System.out.println("all employees will be listed here");
			break;
		case 2:
			//search by ID
			System.out.println("employee with ");
			break;
		case 3:
			//add employee
			addEmployee();
			
			
			
			
		default:
			System.out.println("Sorry, your input was not recognised.");
			break;
		
		}
		
		
		
		try{
		
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
		
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
	
	
	//METHODS

	public static void addEmployee(){
		System.out.println("Please enter a forename up to 30 characters long: ");
		String fnametemp = input.next();
		
		while((fnametemp.length() > 30) || (fnametemp.length() < 1)){
			System.out.println("Name is not a valid length. Please re-enter: ");
			fnametemp = input.nextLine();
		}
		
		System.out.println("Please enter a surname up to 30 characters long: ");
		String snametemp = input.next();
		while((snametemp.length() > 30) || (snametemp.length() < 1)){
			System.out.println("Name is not a valid length. Please re-enter: ");			
			snametemp = input.nextLine();
		}
		
		System.out.println("Please choose a salary ID from the following list : \n1\n2\n3\n ");
		int salaryIDtemp = input.nextInt();
		if(salaryIDtemp <1 || salaryIDtemp > 3){
			System.out.println("Salary is not from the list shown. Please re-enter: ");
			salaryIDtemp = input.nextInt();
		}
	}
	
	
	
	/*
	 * selectAllEmployees method to return all Employees
	 */	
	public static void selectAllEmployees(){
		//String queryString = ("Select * from employees");
		//return queryString;
		try{
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
		}
		catch(SQLException e){
			e.getMessage();
		}
	}
	
	/*
	 * selectQuery method to run select query using different userID's
	 */
	public static String selectEmployeeByID(int userID){
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