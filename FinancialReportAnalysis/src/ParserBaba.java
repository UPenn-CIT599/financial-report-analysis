import java.io.*;
import java.util.*;

/**
 * This Subclass contains methods required to parse data for Alibaba. The data
 * is stored in instance variables within DataParser class. Similar subclasses
 * could be used for other companies
 * 
 * 
 * @author Tim Culpan, Angela Wen
 * 
 */
public class ParserBaba extends DataParser {

	/**
	 * The constructor calls all the following parser methods in order to populate
	 * the instance variables.
	 * 
	 * @param companyDataFile
	 */
	public ParserBaba(String companyDataFile) {
		super(companyDataFile);
		parseRevenue();
		parseNetIncome();
		parseAdjustedNetIncome();
		parseFinancialYearQuarterCompany();
		parseCompStatement();
	}

	/**
	 * this method goes through the file, finds where it says "Revenue" AND is
	 * followed by digits then pulls the second set of digits, which is our target
	 * current Revenue for the period
	 */

	public void parseRevenue() {

		File babaQuarter = new File(filePath);

		String line = null;
		boolean keepLooking = true;
		String targetLine = null;
		String sentence[];

		int indexOfWordRevenue = 0;
		// Note, in this project we're trying a few different methods of finding and
		// grabbing data
		// and before you ask, yes we tried Regex.
		// however: multiple approaches to implementing Regex failed
		// in addition, since regex is relatively resource intense
		// and we only need to grab a number once in each method,
		// we went with a looping approach as the chosen strategy (
		// (let's be frank: NOBODY likes Regex. But who doesn't like loops!! )
		// Anyway, this project is quite memory-hungry, so perhaps this alleviates that
		// appetite a little. Maybe(?!)
		try {

			Scanner scanner = new Scanner(babaQuarter);

			while (scanner.hasNext() && keepLooking) {
				line = scanner.nextLine();

				if (line.startsWith("Revenue") && !(line.contains("year"))) {

					keepLooking = false;
					targetLine = line;

				} // end if

			} // end while
			scanner.close();

			// remove the commas
			targetLine = line.replaceAll(",", "");

			// populate Array "sentence" with each word in the sentence
			// using a space as the delimiter
			sentence = targetLine.split(" ");
			// 0 index is "Revenue"
			// 1 index is previous revenue
			// 2 index is current revenue
			for (int x = 0; x < sentence.length; x++) {
				if ((sentence[x]).equals("Revenue")) {
					indexOfWordRevenue = x;
				}
			}

			// int indexOfPrevRevenue = indexOfWordRevenue + 1;
			int indexOfCurrRevenue = indexOfWordRevenue + 2;
			// int indexOfyoyChangeRevenue = indexOfWordRevenue + 3;

			// prevRevenue = Double.parseDouble(sentence[indexOfPrevRevenue]);
			currRevenue = Double.parseDouble(sentence[indexOfCurrRevenue]);
			// yoyChangeRevenue = Double.parseDouble(sentence[indexOfyoyChangeRevenue]);

		} // end try

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} // end catch
	} // END parseRevenue

	/**
	 * method goes through file, finds the netIncome line and returns it NOTE:
	 * slight variation to parseRevenue approach this approach allows us to pull
	 * prior year and y/y change our project doesn't require this extra data, so can
	 * be adjusted or deleted
	 * 
	 */
	public void parseNetIncome() {
		// note: using a slightly different parsing technique than for parseRevenue
		File babaQuarter = new File(filePath);

		String targetLine = null;
		String sentence[] = null;

		try {
			Scanner scanner = new Scanner(babaQuarter);

			String line = null;
			boolean keepLooping = true;
			while (scanner.hasNext() && (keepLooping)) {

				line = scanner.nextLine();

				if (line.startsWith("Net Income") || line.startsWith("Net income")) {
					targetLine = line;
					keepLooping = false;
				} // end if
			} // end while

			targetLine = line.replaceAll(",", "");
			sentence = targetLine.split(" ");
			// prevNetIncome = Double.parseDouble(sentence[2]);
			currNetIncome = Double.parseDouble(sentence[3]);
			// yoyChangeNetIncomeChange = Double.parseDouble(sentence[4]);

			scanner.close();
		} // end try

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} // end catch

	} // END parseNetIncome method

	/**
	 * this method is more tricky because non-GAAP NetIncome is split over two lines
	 * there's a footnote (x) before the data so, split into a subloop, read the
	 * line backwards (the footnote may not be in all PDFs) line 1:
	 * "NonGAAPNetIncome line 2: the actual data
	 * 
	 */

	public void parseAdjustedNetIncome() {

		File babaQuarter = new File(filePath);

		String targetLine = null;
		String sentence[] = null;

		try {
			Scanner scanner = new Scanner(babaQuarter);
			int counter = 0;

			// two conditions, to ensure we stop after grabbing revenue the first time
			// for Non-GAAP, we are counting once again because we grab the subsequent line
			// AFTER the String "Non-GAAP Net Income" to exit the loop

			String grabLine1 = null;
			String grabLine2 = "notYetFound";
			String grabLine3 = "grabLine3"; // not used in current implementation
			while (scanner.hasNext() && (counter < 2)) {
				String line = scanner.nextLine();

				// this loop only triggers if keyword already found in earlier pass of loop
				if ((counter == 1) && (line.length() > 3)) {
					grabLine2 = line;
					// advance the counter ONCE so that we don't keep reading (ie, stop here)
					counter++;

					int x = grabLine2.length();
					targetLine = grabLine2;

					if ((grabLine2.length() > 5) || (grabLine2.length() > 3 && (grabLine2.contains("(")))) { // this is
																												// a
																												// defacto
																												// empty
																												// test
						targetLine = grabLine2;

					} // end grabLine2 length check

					else {
						targetLine = grabLine1; // if the 2nd GrabLine is empty, means everything is Grabline1
						counter++;
					}

				} // end if "Counter == 1" loop

				// below is loop is our FIRST trigger
				if (line.startsWith("Non-GAAP Net Income") || line.startsWith("Non-GAAP net income")) {
					grabLine1 = line;
					counter++;

				} // end if startsWith

			} // end while

			// remove the commas
			targetLine = targetLine.replaceAll(",", "");
			// turn it into an Array
			sentence = targetLine.split(" ");

			// work BACKWARD
			// pull them as String first, parse to Doubles later

			
			// String yoyPercent = sentence[(sentence.length) - 1]; // not used
			// String yoyChange = sentence[(sentence.length) - 2];
			String currYear = sentence[(sentence.length) - 3];
			
			scanner.close();
			// yoyChangeAdjustedNetIncome = Double.parseDouble(yoyChange);
			// prevAdjustedNetIncome = Double.parseDouble(prevYear);
			currAdjustedNetIncome = Double.parseDouble(currYear);
		} // end try
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} // end catch

	} // END parseAdjustedNetIncome method

	/**
	 * method grabs company name, financial year, financial quarter. Quarter is
	 * converted from word (March,June,September, December) to digit of month
	 * 
	 */
	public void parseFinancialYearQuarterCompany() {

		File babaQuarter = new File(filePath);

		String targetLine = null;
		String sentence[] = null;
		int indexOfCompanyName = 0;
		int indexOfWordResults = 0;
		int indexOfWordQuarter = 0;
		int indexOfYear = 0;
		int indexOfQuarter = 0;
		int quarter = 0;

		try {
			Scanner scanner = new Scanner(babaQuarter);
			;
			// two conditions, to ensure we stop after grabbing revenue the first time
			boolean found = false;
			while (scanner.hasNext() && found == false) {
				String line = scanner.nextLine();

				if (line.contains("Mar") || (line.contains("Jun")) || (line.contains("Sept"))
						|| (line.contains("Dec"))) {
					targetLine = line;
					found = true;

				} // end if

			} // end while

			// populate the Array sentence with each word in that line
			sentence = targetLine.split(" ");

			// loop through that sentence and pull each word according to its place in the
			// Array
			for (int x = 0; x < sentence.length; x++) {
				if ((sentence[x]).equals("Alibaba")) {
					indexOfCompanyName = x;
				}
				if ((sentence[x]).equals("Results")) {
					indexOfWordResults = x;
				}
				if ((sentence[x]).equals("Quarter")) {
					indexOfWordQuarter = x;
				}

			} // end of for
				// search backward to pull year and quarter
			indexOfYear = indexOfWordResults - 1;
			indexOfQuarter = indexOfWordQuarter - 1;
			scanner.close();

			// pulls int for month number of quarter end
			if (sentence[indexOfQuarter].contains("Mar")) {
				quarter = 03;
			} else if (sentence[indexOfQuarter].contains("Jun")) {
				quarter = 06;
			} else if (sentence[indexOfQuarter].contains("Sep")) {
				quarter = 9;
			} else if (sentence[indexOfQuarter].contains("Dec")) {
				quarter = 12;
			}
			// pulls company name
			companyName = (sentence[indexOfCompanyName]);

			finQuarter = quarter;
			companyName = (sentence[indexOfCompanyName]);
			financialYear = Integer.parseInt(sentence[indexOfYear]);

		} // end try

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} // end catch

	} // END parseFinancialYear method

	/**
	 * Method grabs all the information in the initial statement up to and including
	 * the words "highlights" which is the keyword signaling the end of the prose
	 * part prior to data tables
	 * 
	 */
	public void parseCompStatement() {

		File babaQuarter = new File(filePath);

		boolean keepGoing = true;

		String word = null;
		String line = null;
		try {
			Scanner scanner = new Scanner(babaQuarter);
			while (scanner.hasNext() && keepGoing) {
				line = scanner.nextLine();
				if (!line.contains("%") || (line.length() > 60)) {
					word = scanner.next();
					// use simple concatenation to add each word to the string, 
					// adding a space in between each word
					
					compStatement = compStatement + " " + word;
					// stop when we come across the word "Webcast"
					if (word.contentEquals("Webcast") || word.contentEquals("WEBCAST")) {
						keepGoing = false;
					} // ends delimiting if
				} // ends contains %
			}
			// end while loop

			scanner.close(); //close resource
		} // end try

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} // end catch

	} // END parseStatement Method

} // END ParserBaba Class