import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Employee {
	
	private int id;					//Employee ID
	private String forename;		//Employee Forename
	private String lastname;		//Employee Surname
	private BigDecimal salary;			//Employee Salary
	
	
	public Employee(String eForename, String eLastname, BigDecimal eSalary) {
		this.forename = eForename;
		this.lastname = eLastname;
		this.salary = eSalary;
	}
	
	public Employee(int newId, String eForename, String eLastname, BigDecimal eSalary) {
		this(eForename, eLastname, eSalary);
		this.id = newId;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getForename() {
		return forename;
	}
	
	public void setForename(String forename) {
		this.forename = forename;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public BigDecimal getSalary() {
		return salary;
	}
	
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	public static int validateUserID(Scanner input){
		int newInt = -1;
		try{
			newInt = input.nextInt();
		}
		catch(InputMismatchException e)
		{
			System.out.println("Wrong input. Closing system.");
			System.exit(1);
		}
		return newInt;
		
		/*while(!input.hasNextInt()){
            System.out.println("That's not a number!");
            return validateUserID(input);
        }//while
		return input.nextInt();*/
	}
	
	public static String validateStringInput(String name){
		while ((name.length() > 30) || (name.length() < 1) || (name.matches(".*\\d.*"))) {
			System.out.println("Name is not valid. Please re-enter: ");
			name = Main.input.next();
			//Main.input.next();
		}//while
		
		return name;	
	}
	
	public static BigDecimal validateSalaryInput(Scanner input) {
		BigDecimal sal = null;
		try {
			sal = input.nextBigDecimal();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Exiting.");
			System.exit(1);
		}
		return sal;
	}

}
