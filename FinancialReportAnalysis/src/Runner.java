
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * The program utilize two external libraries PDF Box and SentimentAnalysis to
 * read from PDFs and conduct sentiment analysis on financial statements
 * 
 * @author Cryrus Cheng, Angela Wen
 *
 *         Credit to Ruthwik at https://github.com/Ruthwik/Sentiment-Analysis
 * 
 *         Credit to RadixCode at
 *         https://radixcode.com/pdfbox-example-code-how-to-extract-text-from-pdf-file-with-java
 * 
 */
public class Runner {

	private Map<String, FinancialData> finDataHM = new HashMap<String, FinancialData>();
	private Map<String, SentimentAnalysisResult> senResultHM = new HashMap<String, SentimentAnalysisResult>(); // object
	
	/**
	 * The Runner constructor loops through all PDFs in a folder and exports them to
	 * .txt
	 */
	public Runner() throws IOException {
		// Loop through all PDFs in a folder and export to .txt files in folder "txt"
		PDFtoTxt();

		// Loop through all txt files to fill hashmaps
		readTxts();
		
	}

	/**
	 * PDFtoTxt opens all PDFs within the "pdf" folder and creates a txt file with
	 * PDFBoxReadFromFile.
	 * 
	 * @throws IOException
	 */
	private void PDFtoTxt() throws IOException {
		File pdfFolder = new File("pdf");
		for (File file : pdfFolder.listFiles(new PDFFileFilter())) {
			PDFBoxReadFromFile PDFReader = new PDFBoxReadFromFile(file);
			PDFReader.printToTxt(); // Generate a txt file "filename_converted.txt" for use of DataParser
		}
	}

	/**
	 * readTxts opens all txt files, adds to "txt" folder, and initializes
	 * FinancialData and SentimentAnalysis objects after parsing with DataParser. It
	 * then inputs the FinancialData and SentimentAnalysis objects into the
	 * corresponding hashmap.
	 * @throws IOException 
	 */
	private void readTxts() throws IOException {
		File txtFolder = new File("txt");
		for (File file : txtFolder.listFiles(new TxtFileFilter())) {

			ParserBaba parser = new ParserBaba(file.getPath());
			// Use WordCounter class to get HashMap of wordcount and add to FinancialData object
			WordCounter counter = new WordCounter(file);
			
			// Construct financial data object with data from parser and word counter
			FinancialData financialData = new FinancialData(parser.getCurrRevenue(), 
					parser.getCurrNetIncome(), parser.getCurrAdjustedNetIncome(),
					parser.getFinancialYear(), parser.getFinQuarter(), 
					parser.getCompanyName(), parser.getCompStatement(), counter.countOfWords());

			// Create unique key with company name, year, quarter
			String hmKey = financialData.getCompanyName() + "_" + financialData.getFinYear() + "_" + financialData.getFinQuarter();

			// Add appropriate data to financial data hashmap
			finDataHM.put(hmKey, financialData);

			// Run the sentiment Analysis and add to hashmap
			SentimentAnalysisResult sentimentAnalysis = new SentimentAnalysisResult(financialData.getCompStatement());
			sentimentAnalysis.showSentimentScore();
			senResultHM.put(hmKey, sentimentAnalysis);

			// End of the For Loop
		}
	}

	/**
	 * The print method prints the compensation statement to a new file named with
	 * the key of the hashmap entry.
	 * 
	 * @throws FileNotFoundException
	 */
	public void print() throws FileNotFoundException {
		for (String key : finDataHM.keySet()) {
			// create folder dataset
			File folder = new File("dataset");
			folder.mkdir();

			// Print to file
			File out = new File(folder, key + ".txt");

			PrintWriter pw = new PrintWriter(out);

			String text = finDataHM.get(key).getCompStatement();
			pw.print(text);
			pw.flush();
			pw.close();
		}
	}
	
	/**
	 * getFinDataHM returns the value of the Map with the String key representing
	 * name, quarter, year and the FinancialData object representing the data
	 * retrieved after parsing the PDF files
	 * @return
	 */
	public Map<String, FinancialData> getFinDataHM() {
		return finDataHM;
	}

	/**
	 * getFinDataHM returns the value of the Map with the String key representing
	 * name, quarter, year and the SentimentAnalysis object representing the
	 * data retrieved from sentiment analysis
	 * @return
	 */
	public Map<String, SentimentAnalysisResult> getSenResultHM() {
		return senResultHM;
	}

	/**
	 * For testing
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Runner runner = new Runner();
			runner.print();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
