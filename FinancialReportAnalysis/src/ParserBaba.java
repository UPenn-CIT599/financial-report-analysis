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
	
} //END ParserBaba Class
