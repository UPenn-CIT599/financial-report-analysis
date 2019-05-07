/**
 * The FinancialData class represents objects of a PDF file. It includes
 * methods to get and set each of its attributes.
 *
 */
public class FinancialData {

	private double revenue;
	private double netIncome;
	private double adjustedNetIncome;
	private int finYear;
	private int finQuarter;
	private String companyName;
	private String compStatement;
	
	// Default constructor
	public FinancialData() {
		
	}
	
	//TODO Change Runner so it uses this constructor; pending ParserBaba
	//Constructor
	public FinancialData(double revenue, double netIncome, double adjustedNetIncome, int finYear, 
			int finQuarter, String companyName, String compStatement) {
		this.revenue = revenue;
		this.netIncome = netIncome;
		this.adjustedNetIncome = adjustedNetIncome;
		this.finYear = finYear;
		this.finQuarter = finQuarter;
		this.companyName = companyName;
		this.compStatement = compStatement;
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
