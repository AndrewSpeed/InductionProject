import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeCollection<E> extends ArrayList<E> {
	
	/**
	 * default UID for EmployeeCollection
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeCollection(ResultSet results) {
		super();
		try {
			while(results.next()) {
				int empID = results.getInt(1);
				String empForename = results.getString(2);
				String empSurname = results.getString(3);
				BigDecimal empSalary = results.getBigDecimal(4);
				
				Employee emp = new Employee(empID, empForename, empSurname, empSalary);
				this.add(emp);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception: " + e.getMessage());
		}
		
	}

}
