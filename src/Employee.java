
public class Employee {
	
	private int id;					//Employee ID
	private String forename;		//Employee Forename
	private String lastname;		//Employee Surname
	private float salary;			//Employee Salary
	
	public Employee(int newId){
		this();
		setId(newId);
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
	
	private float getSalary() {
		return salary;
	}
	
	private void setSalary(float salary) {
		this.salary = salary;
	}

}
