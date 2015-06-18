import java.math.BigDecimal;


public class SalesEmployee extends Employee {
	
	private float commissionRate;		//Commission Rate
	private float SalesTotal;			//Sales Total
	
	public SalesEmployee (int newId, String eforename, String elastname, BigDecimal esalary, float newcommissionrate, float newsalestotal){
		super(newId, eforename, elastname, esalary);
		this.setCommissionRate(newcommissionrate);
		this.setSalesTotal(newsalestotal);
		
	}

	public float getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(float commissionRate) {
		this.commissionRate = commissionRate;
	}

	public float getSalesTotal() {
		return SalesTotal;
	}

	public void setSalesTotal(float salesTotal) {
		SalesTotal = salesTotal;
	}
	
	public float calculateCommission() {  // Calculate commission
		return SalesTotal * commissionRate;
	}

}
