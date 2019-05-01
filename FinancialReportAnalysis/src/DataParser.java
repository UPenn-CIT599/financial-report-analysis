import java.util.*;

/**
 * This class takes a string or .txt file 
 * and parses the required data 
 * for subsquent use in FinancialData and SentimentAnalysis
 *  * 
 * @author Tim Culpan
 *
 */

public class DataParser {
	
	
	
	
	public double parseRevenue(){	
		
		return 0;
	}
	
	public double parseNetIncome() {
		
		return 0;
	}
	
	//NOTE: CRC says "parseAdjustedNonGAAPNetIncome", written like this to be consistent 
	// with FinancialData class. Can be amended
	public double parseAdjustedNetIncome() {
		
		return 0;		
	}
	
	public int parseFinYear() {
		
		return 0;		
	}
	
	public int parseFinQuarter() {
		
		return 0;		
	}
	
	public String parseCompanyName() {
		
		return null;
		
	}
	
	public String parseCompStatement() {
		
		return null;
	}
	
	//added per CRC, but wonder if it should be in a separate class, 
	//or in a Utility Class 
	public void createWordCount() {		
	}
	
	public void outPutFullStatementText() {
		
	}
	
	

}//END DataParser Class
