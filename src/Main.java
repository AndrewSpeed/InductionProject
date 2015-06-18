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

		System.out
				.println("\nWELCOME TO THE EMPLOYEE MANAGEMENT SYSTEM\n*** Main Menu ***\n");

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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;
			case 3:
				// add employee
				addEmployee();
				break;

			default:
				System.out.println("Invalid option selection, exiting.");
				System.exit(1);
				break;
			}
		}

	}// psvm

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
		String sqlTemplate = "BEGIN; "
				+ "INSERT INTO salaries(salary) VALUES (?); "
				+ "INSERT INTO employees(forename, surname, salaryid) VALUES (?, ?, LAST_INSERT_ID()); "
				+ "COMMIT;";

		PreparedStatement sql;
		try {
			sql = conn.prepareStatement(sqlTemplate);
			sql.setBigDecimal(1, salary);
			sql.setString(2, forename);
			sql.setString(3, surname);
			System.out.println(sql);
			sql.executeQuery();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("User added successfully :).");
	}

	/*
	 * selectAllEmployees method to return all Employees
	 */
	public static void selectAllEmployees() {
		// String queryString = ("Select * from employees");
		// return queryString;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, forename, surname, salary "
							+ "FROM employees "
							+ "INNER JOIN salaries ON employees.salaryid=salaries.salaryid;");
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
		String queryString = ("SELECT id, forename, surname, salary " + 
							  "FROM employees INNER JOIN salaries ON employees.salaryid=salaries.salaryid " +
							  "WHERE id=" + userID + ";");
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

			conn = DriverManager.getConnection(url, "root", "Kainos");

		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}
	}

}// class
