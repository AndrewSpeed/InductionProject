import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	private static Connection conn;
	private static Statement stmt;
	private static final String URL = "jdbc:mysql://localhost:3306/project_employees";

	// default persmissions as this should only be shared within the package
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		runApplication();
	}

	public static void runApplication() {
		// load the JDBC driver
		loadJDBCdriver();

		// setting up connection
		setUpConnection();

		// display welcome message
		System.out
				.println("\nWELCOME TO THE EMPLOYEE MANAGEMENT SYSTEM\n*** Main Menu ***\n");

		while (true) {
			int option = getChoice();
			switch (option) {

			case 1:
				selectAllEmployees();
				break;
			case 2:
				findEmployeeById();
				break;
			case 3:
				addEmployee();
				break;
			default:
				System.out.println("Invalid option selection, exiting.");
				System.exit(1);
				break;
			}
		}
	}

	// METHODS

	public static int getChoice() {
		System.out
				.println("1. List all employees\n2. Search for employee by ID\n3. Add new employee\n\nPlease type your option number: ");
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
		String inputFName = input.next();
		Employee.validateStringInput(inputFName);

		System.out.println("Please enter a surname up to 30 characters long: ");
		String inputSName = input.next();
		Employee.validateStringInput(inputSName);

		System.out.println("Please enter a salary: ");
		BigDecimal inputSalary = Employee.validateSalaryInput(input);

		createEmployeeInDB(inputFName, inputSName, inputSalary);

	}

	public static void createEmployeeInDB(String forename, String surname,
			BigDecimal salary) {
		String salaryInsert = "INSERT INTO salaries(salary) VALUES (?);";
		String employeeInsert = "INSERT INTO employees(forename, surname, salaryid) VALUES (?, ?, ?); ";

		PreparedStatement sql;
		try {
			// return the id of the inserted row
			sql = conn.prepareStatement(salaryInsert,
					Statement.RETURN_GENERATED_KEYS);
			sql.setBigDecimal(1, salary);
			sql.executeUpdate();

			// move the pointer to the first (and only) primary key
			ResultSet rs = sql.getGeneratedKeys();
			rs.next();

			// insert values into the statement
			sql = conn.prepareStatement(employeeInsert);
			sql.setString(1, forename);
			sql.setString(2, surname);
			int salId = rs.getInt(1);
			sql.setInt(3, salId);
			sql.executeUpdate();

			System.out.println("User added successfully :).");
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
		}

	}

	/*
	 * selectAllEmployees method to return all Employees
	 */
	public static void selectAllEmployees() {
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, forename, surname, salary "
							+ "FROM employees "
							+ "INNER JOIN salaries ON employees.salaryid=salaries.salaryid;");
			EmployeeCollection<Employee> employees = new EmployeeCollection(rs);
			for (Employee e : employees) {
				System.out.println(String.format("[%d] %s %s\tSalary: %.2f", e.getId(),
						e.getForename(), e.getLastname(), e.getSalary()));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void findEmployeeById() {
		System.out.println("Please enter the user ID: ");

		int userID = 0;
		try {
			userID = input.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Invalid user id provided, exiting.");
		}

		String queryString = ("SELECT id, forename, surname, salary "
				+ "FROM employees INNER JOIN salaries ON employees.salaryid=salaries.salaryid "
				+ "WHERE id=?;");
		
		try {
			stmt = conn.prepareStatement(queryString);
			((PreparedStatement) stmt).setInt(1, userID);
			ResultSet rs = ((PreparedStatement) stmt).executeQuery();

			while (rs.next()) {
				int empID = rs.getInt("ID");
				String fname = rs.getString("forename");
				String sname = rs.getString("surname");
				int salary = rs.getInt("salary");
				
				System.out.println("Emp ID\tForename Surname Salary\n");
				System.out.println(empID + "\t" + fname + "\t" + sname
						+ "\t" + salary + "\n");
			}
		} catch (SQLException e1) {
			System.err.println(e1.getMessage());
		}
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

			conn = DriverManager.getConnection(URL, "root", "Kainos");

		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}
	}

}// class
