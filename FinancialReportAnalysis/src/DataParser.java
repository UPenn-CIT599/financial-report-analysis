import java.util.*;

/**
 * This class takes a string or .txt file 
 * and parses the required data 
 * for subsequent use in FinancialData and SentimentAnalysis
 *  
 * @author Tim Culpan
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
	
	abstract double parseRevenue();
	abstract double parseNetIncome();
	abstract double parseAdjustedNetIncome();
	abstract int parseFinancialYear();
	abstract String parseCompStatement();
	

}//END DataParser Class
