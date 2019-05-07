import java.io.*;
import java.util.*;


/**
 * Class contains methods required to parse data for Alibaba
 *  
 * @author Tim Culpan
 * currently testing on BABA201409.pdf
 * 
 * includes parsers (aka getters) for companyName, finQuarter 
 */
public class ParserBaba extends DataParser {
	/** 
	 * @param companyDataFile
	 */
	
	String filePath = null;
	
	public ParserBaba(String companyDataFile) {
		super(companyDataFile);
		filePath = companyDataFile;
		// TODO Auto-generated constructor stub
	}

	
	//sought variables
	double currRevenue;
	double currNetIncome;
	double currAdjustedNetIncome;
	
	int financialYear;
	int finQuarter;
	String companyName;
	String compStatement;
	
	//following are created, but we don't have to use them
	double prevRevenue;
	double prevNetIncome;
	double prevAdjustedNetIncome;
		
	double yoyChangeRevenue;
	double yoyChangeNetIncomeChange;
	double yoyChangeAdjustedNetIncome;
	
	//String grabLine;
	
	/**
	 * this method goes through the file, 
	 * finds where it says "Revenue" AND is followed by digits
	 * then pulls the second set of digits, which is our target current Revenue for the period
	 * @return revenue for that period
	 */
	
	public double parseRevenue(){
		
		File babaQuarter = new File(filePath);
		
		String line = null;
		boolean keepLooking = true;
		String targetLine = null;
		String sentence[];
		
		int indexOfWordRevenue = 0;
		
		try {
			
			Scanner scanner = new Scanner(babaQuarter);
			
			while (scanner.hasNext() && keepLooking) {
				line = scanner.nextLine();
				
				if (line.startsWith("Revenue")) {
					
					keepLooking = false;
					targetLine = line;
					
				}//end if
				
			}//end while
				scanner.close();
				
			//remove the commas
			targetLine = line.replaceAll(",", "");
			
			//populate Array "sentence" with each word in the sentence
			//	using a space as the delimiter
			sentence = targetLine.split(" ");
				//0 index is "Revenue"
				//1 index is previous revenue
				//2 index is current revenue
			for (int x=0; x<sentence.length; x++) {
				if ( (sentence[x]).equals("Revenue") ) {
            	indexOfWordRevenue = x;
				}
			}	
			
			int indexOfPrevRevenue = indexOfWordRevenue + 1;
            int indexOfCurrRevenue = indexOfWordRevenue + 2;
            int indexOfyoyChangeRevenue = indexOfWordRevenue + 3;
                
            prevRevenue = Double.parseDouble(sentence[indexOfPrevRevenue]);
            currRevenue = Double.parseDouble(sentence[indexOfCurrRevenue]);
            yoyChangeRevenue = Double.parseDouble(sentence[indexOfyoyChangeRevenue]);
            			
		}//end try
	
		catch (FileNotFoundException e){
			e.printStackTrace();
		} //end catch
		
		//TEST>
		System.out.println("currRevenue is : " + currRevenue + " should be: 16829.0");
		return currRevenue;
	} //END parseRevenue 
	
	/**
	 *method goes through file, finds the netIncome line and returns it
	 *NOTE: slight variation to parseRevenue approach
	 *		this approach allows us to pull prior year and y/y change
	 *		our project doesn't require this extra data, so can be adjusted or deleted
	 *@return netIncome
	 */
public double parseNetIncome(){
		//note: using a slightly different parsing technique than for parseRevenue
		File babaQuarter = new File(filePath);
	
		String targetLine = null;
		String sentence[] = null;
		
		try {
			Scanner scanner = new Scanner(babaQuarter);
			
			String line = null;
			boolean keepLooping = true;
			while(scanner.hasNext() && (keepLooping) ) {
			
				line = scanner.nextLine();

				if (line.startsWith("Net Income")) {
					targetLine = line;
					keepLooping=false;
				}//end if
			}//end while
			
				targetLine = line.replaceAll(",", "");
				sentence = targetLine.split(" ");
				prevNetIncome = Double.parseDouble(sentence[2]);
				currNetIncome = Double.parseDouble(sentence[3]);
				yoyChangeNetIncomeChange = Double.parseDouble(sentence[4]);
			
				scanner.close();
		} //end try

		catch (FileNotFoundException e){
			e.printStackTrace();
		} //end catch
		
		//TEST>
		System.out.println("currNetIncome is: " + currNetIncome + " should be: 3030.0");
		
		return currNetIncome;
	} //END parseNetIncome method

	
/**
 * this method is more tricky because 
 * 		non-GAAP NetIncome is split over two lines
 * 		there's a footnote (x) before the data
 * 			so, split into a subloop,
 * 			read the line backwards (the footnote may not be in all PDFs)
 * line 1: "NonGAAPNetIncome
 * line 2: the actual data
 * 
 * @return AdjustedNetIncome
 */

public double parseAdjustedNetIncome(){
	
	File babaQuarter = new File(filePath);
	
	String targetLine = null;
	String sentence[] = null;
	
	try {
		Scanner scanner = new Scanner(babaQuarter);
		int counter = 0;
		
		//two conditions, to ensure we stop after grabbing revenue the first time
		//for Non-GAAP, we are counting once again because we grab the subsequent line 
		// 		AFTER the String "Non-GAAP Net Income" to exit the loop		
		
		while(scanner.hasNext() && (counter<2) ) {
			//String word = scanner.next();
			String line = scanner.nextLine();

			//this loop only triggers if keyword already found in earlier pass of loop
			if(counter==1) {
				targetLine = line;
				//advance the counter ONCE so that we don't keep reading (ie, stop here)
				counter++;
				
			}
			//this loop is our first trigger
			if (line.startsWith("Non-GAAP Net Income")) {
				//advance the counter again to stop the WHILE loop			
				counter++;
				
			}//end if
			
		}//end while
		
			//remove the commas
			targetLine = targetLine.replaceAll(",", "");
			//turn it into an Array
			sentence = targetLine.split(" ");
			//work BACKWARD
			//pull them as String first, parse to Doubles later
			
			String yoyPercent = sentence[(sentence.length)-1]; //not used
			String yoyChange = sentence[(sentence.length)-2];
			String currYear = sentence[(sentence.length)-3];
			String prevYear = sentence[(sentence.length)-4];
			
			scanner.close();
			yoyChangeAdjustedNetIncome = Double.parseDouble(yoyChange);
			prevAdjustedNetIncome = Double.parseDouble(prevYear);
			currAdjustedNetIncome = Double.parseDouble(currYear);
	} //end try
	catch (FileNotFoundException e){
		e.printStackTrace();
	} //end catch
	
	//TEST>> 
	System.out.println("currAdjustedNetIncome is: " + currAdjustedNetIncome + " should be: 6808.0");
	
	return currAdjustedNetIncome;
} //END parseAdjustedNetIncome method



/**
 * method grabs company name, financial year, financial quarter
 * quarter is converted from word (March,June,September, December) to digit of month
 * 
 * @return financialYear
 */
public int parseFinancialYear(){
	
	File babaQuarter = new File(filePath);
	
	String targetLine = null;
	String sentence[] = null;
	int indexOfCompanyName =0;
    int indexOfWordResults = 0;
    int indexOfWordQuarter =0;
    int indexOfYear =0;
    int indexOfQuarter = 0;
    
    int quarter =0;
    String companyName;
	
	try {
		Scanner scanner = new Scanner(babaQuarter);
		;
		//two conditions, to ensure we stop after grabbing revenue the first time
		boolean found = false;
		while(scanner.hasNext() && found == false ) {
			String line = scanner.nextLine();
		
			if (line.contains("Mar") || (line.contains("Jun") ) || (line.contains("Sept") ) || (line.contains("Dec")) ) {
				targetLine = line;
				found = true;
				
			}//end if
						 
		}//end while
		
		//populate the Array sentence with each word in that line
		sentence = targetLine.split(" ");
		
	        //loop through that sentence and pull each word according to its place in the Array
	        for (int x=0; x<sentence.length; x++) {
	            if ( (sentence[x]).equals("Alibaba") ) {
	            	indexOfCompanyName = x;
	            }
	            if ( (sentence[x]).equals("Results") ) {
	                indexOfWordResults = x;
	            }
	            if ( (sentence[x]).equals("Quarter") ){
	                indexOfWordQuarter = x;
	            }
	            
	        }//end of for
	        //search backward to pull year and quarter
	        indexOfYear = indexOfWordResults - 1;
	        indexOfQuarter = indexOfWordQuarter - 1;
	        scanner.close();
	
			
		
//pulls int for month number of quarter end
		if (sentence[indexOfQuarter].contains("Mar") ) {
			quarter = 03;
		}
		if (sentence[indexOfQuarter].contains("Jun") ) {
			quarter = 06;
		}	
		if (sentence[indexOfQuarter].contains("Sep") ) {
			quarter = 9;				
		}		
		if (sentence[indexOfQuarter].contains("Dec") ) {
			quarter = 12;
		}	
//pulls company name
		companyName = (sentence[indexOfCompanyName]); 			
		
		
	} //end try
		

	catch (FileNotFoundException e){
		e.printStackTrace();
	} //end catch
	
	//TEST>
	finQuarter = quarter;
	companyName = (sentence[indexOfCompanyName]); 
	financialYear = Integer.parseInt(sentence[indexOfYear]);
	
	//TEST>> 	
	System.out.println("financialYear: " + financialYear + " should be: 2014");
	System.out.println("finQuarter: " + finQuarter + " should be: 09");
	System.out.println("companyName: " + companyName + " should be: Alibaba");
	
	return financialYear;
} //END parseFinancialYear method




public int parseFinQuarter() {
	return finQuarter;
}

public String parseCompanyName() {
	return companyName;
}



/**
 * Method grabs all the information in the initial statement
 * up to and including the words "Webcast" 
 * which is the keyword signaling the end of the prose part prior to data tables
 * 
 * @return String of the statement 
 */
public String parseCompStatement(){
	
	File babaQuarter = new File(filePath);
	
	boolean keepGoing = true;
	
	String word = null;
	try {
		Scanner scanner = new Scanner(babaQuarter);
		while(scanner.hasNext() && keepGoing) {
			word = scanner.next();
			//use simple concatenation to add each word to the string, with a space in between.
			compStatement = compStatement + " " + word;
			//stop when we come across the word "Webcast"
				if (word.contentEquals("Webcast")) {
				keepGoing = false;
				}
			}//end while loop
			
			
	scanner.close();
	} //end try

	catch (FileNotFoundException e){
		e.printStackTrace();
	} //end catch
		
	
	return compStatement;
	} //END parseStatement Method
	
} //END ParserBaba Class

