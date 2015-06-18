import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	static Connection conn;
	static Statement stmt;
	static String url = "jdbc:mysql://localhost:3306/project_employees";

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		// Scanner input = new Scanner(System.in);

		// load the JDBC driver
		loadJDBCdriver();

		// setting up connection
		setUpConnection();

		System.out.println("\nWELCOME TO THE EMPLOYEE MANAGEMENT SYSTEM\n*** Main Menu ***\n");
		

		while (true) {
			 int option = getChoice();
			switch (option) {

			case 1:
				// select all employees
				selectAllEmployees();

				break;
			case 2:
				// search by ID

				try {
					stmt = conn.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Please enter the user ID: ");
				int userID = input.nextInt();

				try {
					ResultSet rs = stmt
							.executeQuery(selectEmployeeByID(userID));
					while (rs.next()){
					 int empID = rs.getInt("ID");
					 String fname = rs.getString("forename");
					 String sname = rs.getString("surname");
					 int salaryID = rs.getInt("salaryid");
					 System.out.println("Emp ID\tForename Surname Salary ID\n");
					  System.out.println(empID + "\t" + fname +
                              "\t" + sname + "\t" + salaryID + "\n");
					}


				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			   

				break;
			case 3:
				// add employee
				addEmployee();

			default:
				System.out.println("Sorry, your input was not recognised.");
				System.exit(1);
				break;
			}
		}

	}// psvm

	// METHODS

	
	public static int getChoice(){
		System.out.println("1. List all employees\n2. Search for employee by ID\n3. Add new employee\n\nPlease type your option number: ");
		int option = 0;
		try {
			  option = input.nextInt();
		} catch (InputMismatchException e) {
			System.err.print(e.getMessage());
			System.exit(1);
		}
		return option;
	}
	
	public static void addEmployee() {
		System.out
				.println("Please enter a forename up to 30 characters long: ");
		String fnametemp = input.next();

		while ((fnametemp.length() > 30) || (fnametemp.length() < 1)) {
			System.out.println("Name is not a valid length. Please re-enter: ");
			fnametemp = input.nextLine();
		}

		System.out.println("Please enter a surname up to 30 characters long: ");
		String snametemp = input.next();
		while ((snametemp.length() > 30) || (snametemp.length() < 1)) {
			System.out.println("Name is not a valid length. Please re-enter: ");
			snametemp = input.nextLine();
		}

		System.out
				.println("Please choose a salary ID from the following list : \n1\n2\n3\n ");
		int salaryIDtemp = input.nextInt();
		if (salaryIDtemp < 1 || salaryIDtemp > 3) {
			System.out
					.println("Salary is not from the list shown. Please re-enter: ");
			salaryIDtemp = input.nextInt();
		}
	}

	/*
	 * selectAllEmployees method to return all Employees
	 */
	public static void selectAllEmployees() {
		// String queryString = ("Select * from employees");
		// return queryString;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
			EmployeeCollection<Employee> employees = new EmployeeCollection(rs);
			for (Employee e : employees) {
				System.out.println(String.format("%d %s %s %.2f", e.getId(),
						e.getForename(), e.getLastname(), e.getSalary()));
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	/*
	 * selectQuery method to run select query using different userID's
	 */
	public static String selectEmployeeByID(int userID) {
		String queryString = ("Select * from employees WHERE id =" + userID);
		return queryString;
	}

	/*
	 * loadJDBCdriver method loads the JDBC driver
	 */
	public static void loadJDBCdriver() {
		// load the JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.err.print(e.getMessage());
		}
	}

	/*
	 * setUpConnection method sets up the connection to the database via a URL
	 */
	public static void setUpConnection() {
		// setting up connection
		try {

			conn = DriverManager.getConnection(url, "root", "password");

		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}
	}

}// class