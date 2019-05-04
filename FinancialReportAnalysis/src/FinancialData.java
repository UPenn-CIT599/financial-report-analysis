
public class FinancialData {

	private double revenue;
	private double netIncome;
	private double adjustedNetIncome;
	private int finYear;
	private int finQuarter;
	private String companyName;
	private String compStatement;
	
	
	//Constructor
	public FinancialData() {		
	}


	public double getRevenue() {
		return revenue;
	}


	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}


	public double getNetIncome() {
		return netIncome;
	}


	public void setNetIncome(double netIncome) {
		this.netIncome = netIncome;
	}


	public double getAdjustedNetIncome() {
		return adjustedNetIncome;
	}


	public void setAdjustedNetIncome(double adjustedNetIncome) {
		this.adjustedNetIncome = adjustedNetIncome;
	}


	public int getFinYear() {
		return finYear;
	}


	public void setFinYear(int finYear) {
		this.finYear = finYear;
	}


	public int getFinQuarter() {
		return finQuarter;
	}


	public void setFinQuarter(int finQuarter) {
		this.finQuarter = finQuarter;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getCompStatement() {
		return compStatement;
	}


	public void setCompStatement(String compStatement) {
		this.compStatement = compStatement;
	}
	
	
	
	
}
