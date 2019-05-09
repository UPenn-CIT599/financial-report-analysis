import java.util.Map;

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
	private Map<String, Integer> wordCount;

	//Constructor
	public FinancialData(double revenue, double netIncome, double adjustedNetIncome, int finYear, 
			int finQuarter, String companyName, String compStatement, Map<String, Integer> wordCount) {
		this.revenue = revenue;
		this.netIncome = netIncome;
		this.adjustedNetIncome = adjustedNetIncome;
		this.finYear = finYear;
		this.finQuarter = finQuarter;
		this.companyName = companyName;
		this.compStatement = compStatement;
		this.wordCount = wordCount;
	}

	public double getRevenue() {
		return revenue;
	}

	public double getNetIncome() {
		return netIncome;
	}

	public double getAdjustedNetIncome() {
		return adjustedNetIncome;
	}

	public int getFinYear() {
		return finYear;
	}

	public int getFinQuarter() {
		return finQuarter;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompStatement() {
		return compStatement;
	}

	public Map<String, Integer> getWordCount() {
		return wordCount;
	}
	
	/**
	 * The toString method prints the appropriate values with comma separated
	 * delineators to print to CSV file 
	 */
	@Override
	public String toString() {
		double difference = netIncome - adjustedNetIncome;
		return companyName + "," + finYear + "," + finQuarter + "," + revenue + 
				"," + netIncome + "," + adjustedNetIncome + "," + difference;
	}
	
}
