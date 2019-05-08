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

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.prefs.CsvPreference;

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
	 * Sets up processors for CSV file
	 */
	private static CellProcessor[] getProcessors() {
		final CellProcessor[] processors = new CellProcessor[] {
				new NotNull(), //company name
				new NotNull(), // year
				new NotNull(), // quarter
				new NotNull(), // revenue
				new NotNull(), // net income
				new NotNull(), // adjusted net income
				new NotNull(), // difference between net and adjusted net
				new NotNull(), // sentiment score
				new NotNull(), // sentiment type
				new NotNull(), // very positive
				new NotNull(), // positive
				new NotNull(), // neutral
				new NotNull(), // negative
				new NotNull() // very negative
		};
		
		return processors;
	}
	
	/**
	 * generateCSV prints all financial data and sentiment analysis data to one
	 * CSV file. 
	 * 
	 * For now the lists are hard-coded because there must be a list name so
	 * I am having trouble looping through the hashmaps to create lists when
	 * I cannot create a new list name. Help appreciated!
	 * 
	 * Currently the file will only work if running both 201409 and 201412.
	 * Comment one out to run faster
	 * 
	 * @throws IOException 
	 */
	
	public void generateCSV() throws IOException {
		
		// Construct headers list
		final String[] header = new String[] { "Company", "Year", "Quarter",
			"Revenue (RMB in Millions)", "Net Income (RMB in Millions)", 
			"Adjusted Net Income (RMB in Millions)", 
			"Difference Between Net and Adjusted Income (RMB in Millions)",
			"Sentiment Score", "Sentiment Type", "Very Positive (%)", "Positive (%)",
			"Neutral (%)", "Negative (%)", "Very Negative (%)"};
		
		// Create set of keys in hashmaps to print
		Set<String> keys = new HashSet<String>();
		for (String key : finDataHM.keySet()) {
			keys.add(key);
		}

		// TODO DRY up this part of the code below. As you can see I am going
		// through hashmaps to pull the data and add to separate lists (one for
		// each row) But I cannot loop through because each list needs a unique
		// name that I can't set as the 'key' or any other variable
		
		// Construct Lists for each entry of finDataHM
		List<Object> baba201409 = new ArrayList<Object>();
		baba201409.add(finDataHM.get("Alibaba_2014_9").getCompanyName());
		baba201409.add(finDataHM.get("Alibaba_2014_9").getFinYear());
		baba201409.add(finDataHM.get("Alibaba_2014_9").getFinQuarter());
		baba201409.add(finDataHM.get("Alibaba_2014_9").getRevenue());
		baba201409.add(finDataHM.get("Alibaba_2014_9").getNetIncome());
		baba201409.add(finDataHM.get("Alibaba_2014_9").getAdjustedNetIncome());
		baba201409.add(finDataHM.get("Alibaba_2014_9").getNetIncome() - 
				finDataHM.get("Alibaba_2014_9").getAdjustedNetIncome());
		
		List<Object> baba201412 = new ArrayList<Object>();
		baba201412.add(finDataHM.get("Alibaba_2014_12").getCompanyName());
		baba201412.add(finDataHM.get("Alibaba_2014_12").getFinYear());
		baba201412.add(finDataHM.get("Alibaba_2014_12").getFinQuarter());
		baba201412.add(finDataHM.get("Alibaba_2014_12").getRevenue());
		baba201412.add(finDataHM.get("Alibaba_2014_12").getNetIncome());
		baba201412.add(finDataHM.get("Alibaba_2014_12").getAdjustedNetIncome());
		baba201412.add(finDataHM.get("Alibaba_2014_12").getNetIncome() - 
				finDataHM.get("Alibaba_2014_12").getAdjustedNetIncome());
				
		// Add to Lists for each entry of senResultHM
		baba201409.add(senResultHM.get("Alibaba_2014_9").getSentimentScore());
		baba201409.add(senResultHM.get("Alibaba_2014_9").getSentimentType());
		baba201409.add(senResultHM.get("Alibaba_2014_9").getVeryPositive());
		baba201409.add(senResultHM.get("Alibaba_2014_9").getPositive());
		baba201409.add(senResultHM.get("Alibaba_2014_9").getNeutral());
		baba201409.add(senResultHM.get("Alibaba_2014_9").getNegative());
		baba201409.add(senResultHM.get("Alibaba_2014_9").getVeryNegative());
		
		baba201412.add(senResultHM.get("Alibaba_2014_12").getSentimentScore());
		baba201412.add(senResultHM.get("Alibaba_2014_12").getSentimentType());
		baba201412.add(senResultHM.get("Alibaba_2014_12").getVeryPositive());
		baba201412.add(senResultHM.get("Alibaba_2014_12").getPositive());
		baba201412.add(senResultHM.get("Alibaba_2014_12").getNeutral());
		baba201412.add(senResultHM.get("Alibaba_2014_12").getNegative());
		baba201412.add(senResultHM.get("Alibaba_2014_12").getVeryNegative());

		// Construct ListWriter
		CsvListWriter listWriter = new CsvListWriter(new FileWriter("dataset/allData.csv"), 
				CsvPreference.STANDARD_PREFERENCE);
		
		// Construct processor
		final CellProcessor[] processors = getProcessors();
		
		// Write header
		listWriter.writeHeader(header);;
		
		// Write financial data lists
		listWriter.write(baba201409, processors);
		listWriter.write(baba201412, processors);

		listWriter.close();
		
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

			pw.println("\n\n====================================================\n"
					+ "Comp Statement\n"
					+ "====================================================\n\n");
			pw.flush();
			String text = finDataHM.get(key).getCompStatement();
			pw.print(text);
			pw.flush();
			
			// Print the top word count data from WordCounter here
			Map <String, Integer> topWords = finDataHM.get(key).getWordCount();
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
