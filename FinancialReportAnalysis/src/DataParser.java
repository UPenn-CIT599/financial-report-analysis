import java.util.*;

/**
 * This is an ABSTRACT class takes a string or .txt file and parses the required data 
 * for subsequent use in FinancialData and SentimentAnalysis.
 * 
 * We chose to do an Abstract class with Subclasses for future flexibility
 * 	eg, if we add documents from a different company that follows a different format
 * 
 * It includes accessor methods for each instance variable.
 *  
 * @author Tim Culpan, Angela Wen
 *
 */

abstract class DataParser {
	
	//these are the ones we need for this implementation of the project
	protected String filePath;
	protected double currRevenue;
	protected double currNetIncome;
	protected double currAdjustedNetIncome;
	
	protected int financialYear;
	protected int finQuarter;
	protected String companyName;
	protected String compStatement;
	
	//these are created, but not directly used in this implementation of the project
	protected double prevRevenue;
	protected double prevNetIncome;
	protected double prevAdjustedNetIncome;
		
	protected double yoyChangeRevenue;
	protected double yoyChangeNetIncomeChange;
	protected double yoyChangeAdjustedNetIncome;
	
	//constructor -  
	public DataParser(String companyDataFile) {
		filePath = companyDataFile;
	}

	public String getFilePath() {
		return filePath;
	}

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

	public double getPrevRevenue() {
		return prevRevenue;
	}

	public double getPrevNetIncome() {
		return prevNetIncome;
	}

	public double getPrevAdjustedNetIncome() {
		return prevAdjustedNetIncome;
	}

	public double getYoyChangeRevenue() {
		return yoyChangeRevenue;
	}

	public double getYoyChangeNetIncomeChange() {
		return yoyChangeNetIncomeChange;
	}

	public double getYoyChangeAdjustedNetIncome() {
		return yoyChangeAdjustedNetIncome;
	}
	

}//END DataParser Class
