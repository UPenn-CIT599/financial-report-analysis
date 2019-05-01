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
	 * @return revenue for that period
	 */
	public double parseRevenue(){
		File babaQuarter = new File(filename);
		
		
		//our regex: 
		//start of a line, starts with "Revenue", followed by a space, followed by 1 or more digits
		//thus: ^Revenue\s\d{1,}
		
		Pattern revenue = Pattern.compile(^Revenue\s\d{1,});
	
		
		try {
			Scanner scanner = new Scanner(babaQuarter);
			int counter = 0;
			//two conditions, to ensure we just stop after grabbing revenue the first time
			String targetLine;
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
			////targetLine should be: Revenue 10950 16829 2742 53.7% 


			int x;
			int space;
			String revString;
			

			for (x=0; x<lineToParse.size(); x++) {
				if ((lineToParse.get(x)) == (" ") ){
					space++;
				} 
				if (space==2) {
					revString = revString + (lineToParse.get(x));
				}

			} //end for

			int z = 0;
			for (Integer z: lineToParse){
				double revenue = revArray.toDouble();
			}

			//we know that CURRENT revenue is the SECOND block of digits

			double babaRev;




			
		} //end try

		catch (FileNotFoundException e){
			e.printStackTrace();
		} //end catch


	  	
		return 0;
		
	} //END parseRevenue method
	
	
} //END ParserBaba Class
