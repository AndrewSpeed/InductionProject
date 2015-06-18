import java.math.BigDecimal;


public class Employee {
	
	private int id;					//Employee ID
	private String forename;		//Employee Forename
	private String lastname;		//Employee Surname
	private BigDecimal salary;			//Employee Salary
	
	public Employee(int newId, String eForename, String eLastname, BigDecimal eSalary) {
		this.id = newId;
		this.forename = eForename;
		this.lastname = eLastname;
		this.salary = eSalary;
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

}
