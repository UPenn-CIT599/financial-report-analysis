import java.util.Map;

/**
 * The FinancialData class represents objects of a PDF file. It includes methods
 * to get and set each of its attributes.
 *
 */
public class FinancialData {

	//we chose Double instead of int to give more freedom to manipulate data later
	private double revenue;
	private double netIncome;
	private double adjustedNetIncome;
	
	private int finYear;
	private int finQuarter;
	
	private String companyName;
	private String compStatement;
	//this is the perfect case study for when and how to use a HashMap
	private Map<String, Integer> wordCount;

	/**
	 * The constructor initializes all instance variables with parameters passed in.
	 * 
	 * @param revenue
	 * @param netIncome
	 * @param adjustedNetIncome
	 * @param finYear
	 * @param finQuarter
	 * @param companyName
	 * @param compStatement
	 * @param wordCount
	 */
	public FinancialData(double revenue, double netIncome, double adjustedNetIncome, int finYear, int finQuarter,
			String companyName, String compStatement, Map<String, Integer> wordCount) {
		this.revenue = revenue;
		this.netIncome = netIncome;
		this.adjustedNetIncome = adjustedNetIncome;
		this.finYear = finYear;
		this.finQuarter = finQuarter;
		this.companyName = companyName;
		this.compStatement = compStatement;
		this.wordCount = wordCount;
	}

	// Accessor methods for all instance variables below
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
		return companyName + "," + finYear + "," + finQuarter + "," + revenue + "," + netIncome + ","
				+ adjustedNetIncome + "," + difference;
	}

}
