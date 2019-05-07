import java.util.*;

/**
 * This class takes a string or .txt file and parses the required data 
 * for subsequent use in FinancialData and SentimentAnalysis.
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
	
	//following are created, but we don't have to use them
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
