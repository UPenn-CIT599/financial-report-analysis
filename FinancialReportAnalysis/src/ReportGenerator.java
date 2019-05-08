import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

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
	 * The ReportGenerator constructor initializes the instance variables to
	 * contain the same variables as Runner. It calls generateCSV() to create
	 * a CSV file containing all the appropriate data from hashmaps. It also 
	 * calls generateTxtReprorts() to generate one report for each entry.
	 * @param finDataHM
	 * @param senResultHM
	 * @throws FileNotFoundException 
	 */
	public ReportGenerator (Map<String, FinancialData> finDataHM, 
			Map<String, SentimentAnalysisResult> senResultHM) throws FileNotFoundException {
		this.finDataHM = finDataHM;
		this.senResultHM = senResultHM;
	}
	
	/**
	 * generateCSV prints all financial data and sentiment analysis data to one
	 * CSV file. 
	 */
	
	//TODO
	public void generateCSV() {
		
	}
	
	/**
	 * generateTxtReports creates one txt file per entry in hashmap containing
	 * the compStatement from the PDf as well as the most frequent significant
	 * words in the PDF. 
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

			String text = finDataHM.get(key).getCompStatement();
			pw.print(text);
			pw.flush();
			
			// Print the top word count data from WordCounter here
			Map <String, Integer> topWords = finDataHM.get(key).getWordCount();
			pw.println("\n\n================================================\n"
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
