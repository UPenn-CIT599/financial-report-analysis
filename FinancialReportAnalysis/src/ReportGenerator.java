import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * The ReportGenerator class generates an overall .csv file, as well as a .csv
 * file for each PDF analyzed describing the two HashMaps of financial data
 * and sentiment analysis it contains.
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
	 * file under the dataset folder.
	 * 
	 * @throws IOException
	 */

	public void generateCSV() throws IOException {

		// Construct printer object
		CSVPrinter printer = new CSVPrinter(new FileWriter("dataset/allData.csv"), CSVFormat.EXCEL);

		List<String[]> dataToPrint = new ArrayList<String[]>();

		final String[] header = new String[] { "Company", "Year", "Quarter", "Revenue (RMB in Millions)",
				"Net Income (RMB in Millions)", "Adjusted Net Income (RMB in Millions)",
				"Difference Between Net and Adjusted Income (RMB in Millions)", "Sentiment Score", "Sentiment Type",
				"Very Positive (%)", "Positive (%)", "Neutral (%)", "Negative (%)", "Very Negative (%)" };
		dataToPrint.add(header);

		// Create list of PDFs
		List<String> allFiles = new ArrayList<String>();

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
	 * generateWordCountCSVs takes the top 10 most common words for each PDF file
	 * parsed and prints them with their counts to a CSV file.
	 * 
	 * @throws IOException
	 * 
	 */
	public void generateWordCountCSVs() throws IOException {

		for (String key : finDataHM.keySet()) {
			// Print the top word count data from WordCounter here
			Map<String, Integer> topWords = finDataHM.get(key).getWordCount();

			// Create file name
			String csvName = "dataset/" + key + "_WordCount.csv";

			// Construct printer object
			CSVPrinter printer = new CSVPrinter(new FileWriter(csvName), CSVFormat.EXCEL);

			List<String> words = new ArrayList<String>();
			List<Integer> counts = new ArrayList<Integer>();

			// Add all keys and values to the appropriate lists
			for (String word : topWords.keySet()) {
				words.add(word);
				counts.add(topWords.get(word));
			}

			// Print all
			printer.printRecord(words);
			printer.printRecord(counts);

			printer.close();
		}
	}

}
