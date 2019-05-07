import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class contains methods required to parse data for Alibaba
 *  
 * @author Tim Culpan
 * currently testing on BABA201409.pdf
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

	/**
	 * this method goes through the file, 
	 * finds where it says "Revenue" AND is followed by digits
	 * then pulls the second set of digits, which is our target current Revenue for the period
	 * @return revenue for that period
	 */
	double currRevenue;
	double currNetIncome;
	double currNonGAAPNetIncome;
	
	//following are created, but we don't have to use them
	//if delete, then need to adjust code below
	double prevRevenue;
	double prevNetIncome;
	double numNetIncomeChange;
	
	//created, by not all are needed
	double prevNonGAAPNetIncome;
	double numNonGAAPNetIncomeChange;
	
	String longWord;
	String grabLine;
	
	public double parseRevenue(){
		
		File babaQuarter = new File(filePath);
		
		//File babaQuarter = new File("201409_converted.txt");
		//our regex: 
		//start of a line, starts with "Revenue", followed by a space, followed by 1 or more digits
		//thus: ^Revenue\s\d{1,}
		
//		Pattern revenuePattern = Pattern.compile("Revenue\\s\\d{1,}");
//		String targetLine = null;
		String line = null;
		boolean keepLooking = true;
		String targetLine = null;
		String sentence[];
		
		int indexOfWordRevenue = 0;
		
		//parseRevenue version ONE
		try {
			
			Scanner scanner = new Scanner(babaQuarter);
			
			while (scanner.hasNext() && keepLooking) {
				line = scanner.nextLine();
				
				if (line.startsWith("Revenue")) {
					
					keepLooking = false;
					targetLine = line;
					
				}//end if
				
			}//end while
				
			//remove the commas
			targetLine = line.replaceAll(",", "");
			
			//populate Array "sentence" with each word in the sentence
			//	using a space as the delimiter
			sentence = targetLine.split(" ");
				//0 index is "Revenue"
				//1 index is "10950" - previous revenue
				//2 index is "16829" - current revenue
			for (int x=0; x<sentence.length; x++) {
				if ( (sentence[x]).equals("Revenue") ) {
            	indexOfWordRevenue = x;
				}
			}	
			
			int indexOfPrevRevenue = indexOfWordRevenue + 1;
            int indexOfCurrRevenue = indexOfWordRevenue + 2;
                
             prevRevenue = Double.parseDouble(sentence[indexOfPrevRevenue]);
            currRevenue = Double.parseDouble(sentence[indexOfCurrRevenue]);
            			
		}//end try
	
		catch (FileNotFoundException e){
			e.printStackTrace();
		} //end catch
		
		System.out.println("Revenue is : " + currRevenue + " should equal 16829.0");
		return currRevenue;
	}
	 //END parseRevenue version TWO
		
//	
//	//parseRevenue version ONE
//		
//		try {
//			Scanner scanner = new Scanner(babaQuarter);
//			//two conditions, to ensure we stop after grabbing revenue the first time
//			
//			while(scanner.hasNext() && (keepLooking) ) {
//				String word = scanner.next();
//				line = scanner.nextLine();
//
//				//check whether the next LINE matches our revenue regex
//				Matcher m = revenuePattern.matcher(line);
//				///if it does, then copy the whole line for later parsing
//				if (m.find()) {
//					targetLine = line;
//					keepLooking = false;
//
//				}//end if
//
//				// for BABA201409, targetLine should be: Revenue 10,950 16,829 2,742 53.7% 
//			}//end while
//			
//	
//			// Either of these options should work
//			// but NEITHER works
//			targetLine = targetLine.replaceAll(",", "");
//			//targetLine = line.replaceAll(",", "");
//			
//			//targetLine should now be: Revenue 10950 16829 2742 53.7% 
//
//			//create regex for pulling the digits separately
//			//there may be a better regex, but this SHOULD WORK!
//			Pattern revPatt = Pattern.compile("[^\\d]*[\\d]+[^\\d]+([\\d]+)");
//			Matcher revMatch = revPatt.matcher(targetLine);
//			 				
//				//use "if" because it'll stop at first time regex is matched
//				//if we use 'while', it'll keep running and capture later data 
//				if(revMatch.find()) {
//					currRev = Double.parseDouble(revMatch.group(1));
//					
//					//currRev should be 16829
//				}//end if	
//			
//			scanner.close();
//		} //end try
//
//		catch (FileNotFoundException e){
//			e.printStackTrace();
//		} //end catch
//		return currRev;
//	} //END parseRevenue method
	
	
	/**
	 *method goes through file, finds the netIncome line and returns it
	 *NOTE: slight variation to parseRevenue approach
	 *		this approach allows us to pull prior year and y/y change
	 *		our project doesn't require this extra data, so can be adjusted or deleted
	 *@return netIncome
	 */
public double parseNetIncome(){
		
		File babaQuarter = new File(filePath);
	
		//File babaQuarter = new File("201409_converted.txt");
		//our regex: 
		//start of a line, starts with "Net Income", followed by a space, followed by 1 or more digits
		//thus: ^Net Income\s\d{1,}
		
		Pattern netIncome = Pattern.compile("Net Income\\s");
		String targetLine = null;
		String sentence[] = null;
		
		try {
			Scanner scanner = new Scanner(babaQuarter);
			int counter = 0;
			int xcounter= 0; //throwing this in to count loops. remove before shipping
			//two conditions, to ensure we stop after grabbing revenue the first time
			String line = null;
			boolean keepLooping = true;
			while(scanner.hasNext() && (keepLooping) ) {
				//String word = scanner.next();
				line = scanner.nextLine();

				//check whether the next word matches our netIncome regex
				//Matcher m = netIncome.matcher(line);
				///if it does, then copy the whole line for later parsing
				if (line.startsWith("Net Income")) {
					targetLine = line;
					//advance the counter so that our while loop stops
					//maybe there's a more elegant way, such as a boolean.
					keepLooping=false;
					System.out.println("targetLine inside loop:" + targetLine);
					
				}//end if
				

				//targetLine should be: Net Income 4,937 3,030 494 (38.6%)* 
			}//end while
			
				targetLine = line.replaceAll(",", "");
				System.out.println("targetLine outside loop:" + targetLine);
		
				sentence = targetLine.split(" ");
				prevNetIncome = Double.parseDouble(sentence[2]);
				currNetIncome = Double.parseDouble(sentence[3]);
			
					scanner.close();
		} //end try

		catch (FileNotFoundException e){
			e.printStackTrace();
		} //end catch
		System.out.println("Net Income is: " + currNetIncome + "should be: 3030.0");
		
		return currNetIncome;
	} //END parseNetIncome method
	
/**
 * method is more tricky because non-GAAP NetIncome is split over two lines
 * line 1: "NonGAAPNetIncome
 * line 2: the actual data
 * 
 * @return nonGAAPNetIncome
 */

public double parseAdjustedNetIncome(){
	
	File babaQuarter = new File(filePath);
	
	//File babaQuarter = new File("201409_converted.txt");
	//our regex: 
	//we merely search for ^Non-GAAP Net Income and a space
	//we pull the numbers from the subsequent line
	
	//Pattern nonGAAPNetIncome = Pattern.compile("^Non-GAAP Net Income\\s");
	String targetLine = null;
	String sentence[] = null;
	
	try {
		Scanner scanner = new Scanner(babaQuarter);
		int counter = 0;
		
		//two conditions, to ensure we stop after grabbing revenue the first time
		//for Non-GAAP, we are counting once again because we grab the subsequent line 
		// 		AFTER the String "Non-GAAP Net Income"
		
		
		while(scanner.hasNext() && (counter<2) ) {
			//String word = scanner.next();
			String line = scanner.nextLine();

			//check whether the next word matches our netIncome regex
			//Matcher m = nonGAAPNetIncome.matcher(word);
			///if it does, then copy the whole line for later parsing
			if(counter==1) {
				targetLine = line;
				//advance the counter ONCE MORE so that we don't keep reading (ie, stop here)
				counter++;
				System.out.println("targetLine in while counter =1 : " + targetLine );
			}
			if (line.startsWith("Non-GAAP Net Income")) {
				//advance the counter				
				counter++;
				System.out.println("targetLine in while found targetWord : " + targetLine );
			}//end if
			//System.out.println("targetLine in while: " + targetLine );
			//targetLine should be: (5) 5,893 6,808 1,109 15.5% 
		}//end while
		
			//remove the commas
			System.out.println("targetLine outside while: " + targetLine );
			targetLine = targetLine.replaceAll(",", "");
			sentence = targetLine.split(" ");
			for (int i = sentence.length-1; i>0; i-- ) {
				System.out.print(sentence[i] + " ");
			}
			
			String yoyPercent = sentence[(sentence.length)-1];
			String yoyChange = sentence[(sentence.length)-2];
			String currYear = sentence[(sentence.length)-3];
			String prevYear = sentence[(sentence.length)-4];
			
			
			System.out.println("yoyPercent : " + yoyPercent);
			System.out.println("yoyChange : " + yoyChange);
			System.out.println("currYear : " + currYear);
			System.out.println("prevYear : " + prevYear);
			
			
			scanner.close();
			
			currNonGAAPNetIncome = Double.parseDouble(currYear);
	} //end try
	catch (FileNotFoundException e){
		e.printStackTrace();
	} //end catch
	return currNonGAAPNetIncome;
} //END parseAdjustedNetIncome method



////\\\\FINANCIAL YEAR \\\\
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
	
	int year = Integer.parseInt(sentence[indexOfYear]);
	
	
	return year;
} //END parseFinancialYear method

/**
 * Method grabs all the information in the initial statement
 * up to and including the words "Webcast" 
 * which is the keyword signaling the end of the prose part prior to data tables
 * 
 * @return String of the statement 
 */
public String parseCompStatement(){
	
	//code "babaQuarter"  
	//and scanner as needed
	File babaQuarter = new File(filePath);
	
	boolean keepGoing = true;
	//redundant after using String concatenation
	//ArrayList<String> statement = new ArrayList<>();
	//String longWord = null;
	String word = null;
	try {
		Scanner scanner = new Scanner(babaQuarter);
		//while (keepGoing) {
			while(scanner.hasNext() && keepGoing) {
			word = scanner.next();
			longWord = longWord + " " + word;
			//stop when we come across the word "Webcast"
				if (word.contentEquals("Webcast")) {
				keepGoing = false;
				}
			}//end hasNext() loop
		//}//end keepGoing loop	
			
	scanner.close();
	} //end try

	catch (FileNotFoundException e){
		e.printStackTrace();
	} //end catch
		
	// now redundant after using String concatenation
	//String statementAsString = String.join(" ", statement);
	
	return longWord;
	} //END parseStatement Method
	
} //END ParserBaba Class





















