import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * The ReportGenerator class generates a .csv file and a .txt file describing
 * the two HashMaps of financial data and sentiment analysis it contains.
 * 
 * @author Angela Wen
 *
 */

public class ReportGenerator {

	private Map<String, FinancialData> finDataHM;
	private Map<String, SentimentAnalysisResult> senResultHM;

	/**
	 * The ReportGenerator constructor initializes the instance variables to contain
	 * the same variables as Runner. 
	 * 
	 * @param finDataHM
	 * @param senResultHM
	 * @throws FileNotFoundException
	 */
	public ReportGenerator(Map<String, FinancialData> finDataHM, Map<String, SentimentAnalysisResult> senResultHM)
			throws FileNotFoundException {
		this.finDataHM = finDataHM;
		this.senResultHM = senResultHM;
	}

	/**
	 * generateCSV prints all financial data and sentiment analysis data to one CSV
	 * file.
	 * 
	 * Currently the file will only work if only running 201409, 201412, 201512, 201612.
	 * The values are hard-coded in. 
	 * 
	 * @throws IOException
	 */

	public void generateCSV() throws IOException {

		// Construct printer object
		CSVPrinter printer = new CSVPrinter(new FileWriter("dataset/allData.csv"), CSVFormat.EXCEL);
		
		List<String[]> dataToPrint = new ArrayList<String[]>();
		
		final String[] header = new String[] { "Company", "Year", "Quarter",
				"Revenue (RMB in Millions)", "Net Income (RMB in Millions)", 
				"Adjusted Net Income (RMB in Millions)", 
				"Difference Between Net and Adjusted Income (RMB in Millions)",
				"Sentiment Score", "Sentiment Type", "Very Positive (%)", "Positive (%)",
				"Neutral (%)", "Negative (%)", "Very Negative (%)"};
		dataToPrint.add(header);
		
		// Create list of PDFs
		List<String> allFiles = new ArrayList<String> ();
		
		// Iterate through hashmaps
		for (String key : finDataHM.keySet()) {
		   String stringName = (key.replaceAll("_", ""));
		   stringName = finDataHM.get(key).toString() + "," + senResultHM.get(key).toString();
		   allFiles.add(stringName);
		}
		
		// Iterate through list of PDFs and add data to other list
		for (String file : allFiles) {
			dataToPrint.add(file.split(","));
		}
		
		// Print to CSV
		printer.printRecords(dataToPrint);
		
		printer.close(true);

	}

	/**
	 * generateTxtReports creates one txt file per entry in hashmap containing the
	 * compStatement from the PDf as well as the most frequent significant words in
	 * the PDF.
	 * 
	 * @throws FileNotFoundException
	 */
	public void generateTxtReports() throws FileNotFoundException {
		for (String key : finDataHM.keySet()) {
			// create folder dataset
			File folder = new File("dataset");
			folder.mkdir();

			// Print comp statement to file
			File out = new File(folder, key + ".txt");

			PrintWriter pw = new PrintWriter(out);

			pw.println("====================================================\n" + "Comp Statement\n"
					+ "====================================================\n\n");
			pw.flush();
			String text = finDataHM.get(key).getCompStatement();
			pw.print(text);
			pw.flush();

			// Print the top word count data from WordCounter here
			Map<String, Integer> topWords = finDataHM.get(key).getWordCount();
			pw.println("\n\n====================================================\n"
					+ "Below is the count of top words, minus most common:\n\n");
			pw.flush();
			pw.printf("%-10s%10s", "Word", "Count\n");
			pw.flush();
			for (String word : topWords.keySet()) {
				pw.printf("%-10s%10d", word, topWords.get(word));
				pw.flush();
				pw.println();
				pw.flush();
			}
			pw.close();
		}
	}

}
