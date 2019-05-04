import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class contains methods required to parse data for Alibaba
 *  
 * @author Tim Culpan
 *
 */
public class ParserBaba extends DataParser {

	/**
	 * 
	 * @param companyDataFile
	 */
	public ParserBaba(String companyDataFile) {
		super(companyDataFile);
		// TODO Auto-generated constructor stub
	}

	/**
	 * this method goes through the file, 
	 * finds where it says "Revenue" AND is followed by digits
	 * then pulls the second set of digits, which is our target current Revenue for the period
	 * @return revenue for that period
	 */
	double currRev;
	double currNetIncome;
	double currNonGAAPNetIncome;
	
	//following are created, but we don't have to use them
	//if delete, then need to adjust code below
	double prevNetIncome;
	double numNetIncomeChange;
		
	
	//created, by not all are needed
	double prevNonGAAPNetIncome;
	double numNonGAAPNetIncomeChange;
	
	
	
	public double parseRevenue(){
		
		File babaQuarter = new File("201409_converted.txt");
		//our regex: 
		//start of a line, starts with "Revenue", followed by a space, followed by 1 or more digits
		//thus: ^Revenue\s\d{1,}
		
		Pattern revenue = Pattern.compile("^Revenue\\s\\d{1,}");
		String targetLine = null;
		
		try {
			Scanner scanner = new Scanner(babaQuarter);
			int counter = 0;
			//two conditions, to ensure we stop after grabbing revenue the first time
			
			while(scanner.hasNext() && (counter==0) ) {
				String word = scanner.next();
				String line = scanner.nextLine();

				//check whether the next word matches our revenue regex
				Matcher m = revenue.matcher(word);
				///if it does, then copy the whole line for later parsing
				if (m.find()) {
					targetLine = line;
					//advance the counter so that our while loop stops
					//maybe there's a more elegant way, such as a boolean.
					counter++;

				}//end if

				//targetLine should be: Revenue 10,950 16,829 2,742 53.7% 
			}//end while

			targetLine = targetLine.replaceAll(",", "");
			
			//targetLine should be: Revenue 10950 16829 2742 53.7% 

			//create regex for pulling the digits separately
			//there may be a better regex, but this works!
			Pattern revPatt = Pattern.compile("[^\\d]*[\\d]+[^\\d]+([\\d]+)");
			Matcher revMatch = revPatt.matcher(targetLine);
			 
				
				//use "if" because it'll stop at first time regex is matched
				//if we use 'while', it'll keep running and capture later data 
				if(revMatch.find()) {
					currRev = Double.parseDouble(revMatch.group(1));
					
					//currRev should be 16829
				}//end if	
			
			scanner.close();
		} //end try

		catch (FileNotFoundException e){
			e.printStackTrace();
		} //end catch
		return currRev;
	} //END parseRevenue method
	
	
	/**
	 *method goes through file, finds the netIncome line and returns it
	 *NOTE: slight variation to parseRevenue approach
	 *		this approach allows us to pull prior year and y/y change
	 *		our project doesn't require this extra data, so can be adjusted or deleted
	 *@return netIncome
	 */
public double parseNetIncome(){
		
		File babaQuarter = new File("201409_converted.txt");
		//our regex: 
		//start of a line, starts with "Net Income", followed by a space, followed by 1 or more digits
		//thus: ^Net Income\s\d{1,}
		
		Pattern netIncome = Pattern.compile("^Net Income\\s\\d{1,}");
		String targetLine = null;
		
		try {
			Scanner scanner = new Scanner(babaQuarter);
			int counter = 0;
			//two conditions, to ensure we stop after grabbing revenue the first time
			
			while(scanner.hasNext() && (counter==0) ) {
				String word = scanner.next();
				String line = scanner.nextLine();

				//check whether the next word matches our netIncome regex
				Matcher m = netIncome.matcher(word);
				///if it does, then copy the whole line for later parsing
				if (m.find()) {
					targetLine = line;
					//advance the counter so that our while loop stops
					//maybe there's a more elegant way, such as a boolean.
					counter++;

				}//end if

				//targetLine should be: Net Income 4,937 3,030 494 (38.6%)* 
			}//end while
			
				//removed the commas
				targetLine = targetLine.replaceAll(",", "");
			
			 	Pattern netIncomePatt = Pattern.compile("[^Net Income][0-9]\\d+");
		        Matcher netIncomeMatch = netIncomePatt.matcher(targetLine);
			
					//by using this approach, we can actually pull multiple fields, if need be
		        	//note that it require a "while" loop and a counter
					int count = 0;
					
					while(netIncomeMatch.find()) {
					    
					    if (count == 0) {
					        prevNetIncome = Double.parseDouble(netIncomeMatch.group());
					        }
					    if (count==1) {
					        currNetIncome = Double.parseDouble(netIncomeMatch.group());
					        }
					    if (count == 2) {
					        numNetIncomeChange = Double.parseDouble(netIncomeMatch.group());
					        }
					   count++;
					    
					   //currNetIncome should be 3030
					    }//END WHILE 
						scanner.close();
		} //end try

		catch (FileNotFoundException e){
			e.printStackTrace();
		} //end catch
		return currNetIncome;
	} //END parseNetIncome method
	
/**
 * method is more tricky because non-GAAP NetIncome is split over two lines
 * line 1: "NonGAAPNetIncome
 * line 2: the actual data
 * 
 * @return nonGAAPNetIncome
 */

public double parseNonGAAPNetIncome(){
	
	File babaQuarter = new File("201409_converted.txt");
	//our regex: 
	//we merely search for ^Non-GAAP Net Income and a space
	//we pull the numbers from the subsequent line
	
	Pattern nonGAAPNetIncome = Pattern.compile("^Non-GAAP Net Income\\s");
	String targetLine = null;
	
	try {
		Scanner scanner = new Scanner(babaQuarter);
		int counter = 0;
		
		//two conditions, to ensure we stop after grabbing revenue the first time
		//for Non-GAAP, we are counting once again because we grab the subsequent line 
		// 		AFTER the String "Non-GAAP Net Income"
		
		
		while(scanner.hasNext() && (counter<2) ) {
			String word = scanner.next();
			String line = scanner.nextLine();

			//check whether the next word matches our netIncome regex
			Matcher m = nonGAAPNetIncome.matcher(word);
			///if it does, then copy the whole line for later parsing
			if (m.find()) {
				//advance the counter				
				counter++;

			}//end if
			//now with counter at 1, we can grab the NEXT line
			if(counter==1) {
				targetLine = line;
				//advance the counter ONCE MORE so that we don't keep reading (ie, stop here)
				counter++;
			}

			//targetLine should be: (5) 5,893 6,808 1,109 15.5% 
		}//end while
		
			//remove the commas
			targetLine = targetLine.replaceAll(",", "");
			
	        //regex pulls each group of digits, minimum of three
	        Pattern nonGaapPatt = Pattern.compile("\\d{3,}");
	        Matcher nonGaapMatch = nonGaapPatt.matcher(targetLine);
	       
				 
			
			
			//by using this approach, we can actually pull multiple fields, if need be
				int count = 0;
				while(nonGaapMatch.find()) {
				    
	 			    if (count == 0) {
	 			        prevNonGAAPNetIncome = Double.parseDouble(nonGaapMatch.group());
	 			        }
	 			    if (count==1) {
				        currNonGAAPNetIncome = Double.parseDouble(nonGaapMatch.group());
				        }
				    if (count == 2) {
				    	numNonGAAPNetIncomeChange = Double.parseDouble(nonGaapMatch.group());
				        }
				   
				     count++;
				     
				     //currNonGAAPNetIncome should be 6808
				    
				    }//END WHILE 
				   
					scanner.close();
	} //end try

	catch (FileNotFoundException e){
		e.printStackTrace();
	} //end catch
	return currNonGAAPNetIncome;
} //END parseNonGAAPNetIncome method


	
	
	
	
} //END ParserBaba Class
