/**
 * This class takes a string or .txt file and parses the required data for
 * subsequent use in FinancialData and SentimentAnalysis.
 * 
 * It includes accessor methods for each instance variable.
 * 
 * @author Tim Culpan, Angela Wen
 *
 */

abstract class DataParser {

	protected String filePath;
	protected double currRevenue;
	protected double currNetIncome;
	protected double currAdjustedNetIncome;

	protected int financialYear;
	protected int finQuarter;
	protected String companyName;
	protected String compStatement;

	// constructor -
	public DataParser(String companyDataFile) {
		filePath = companyDataFile;
	}

	// Accessor methods for all instance variables below

	public double getCurrRevenue() {
		return currRevenue;
	}

	public double getCurrNetIncome() {
		return currNetIncome;
	}

	public double getCurrAdjustedNetIncome() {
		return currAdjustedNetIncome;
	}

	public int getFinancialYear() {
		return financialYear;
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

}// END DataParser Class
