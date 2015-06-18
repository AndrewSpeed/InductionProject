import java.math.BigDecimal;


public class Employee {
	
	private int id;					//Employee ID
	private String forename;		//Employee Forename
	private String lastname;		//Employee Surname
	private BigDecimal salary;			//Employee Salary
	
	public Employee(int newId){
		setId(newId);
	}
	
	public Employee(int newId, String eForename, String eLastname, BigDecimal eSalary) {
		this(newId);
		this.forename = eForename;
		this.lastname = eLastname;
		this.salary = eSalary;
	}
	
	private int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	private String getForename() {
		return forename;
	}
	
	private void setForename(String forename) {
		this.forename = forename;
	}
	
	private String getLastname() {
		return lastname;
	}
	
	private void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	private BigDecimal getSalary() {
		return salary;
	}
	
	private void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
