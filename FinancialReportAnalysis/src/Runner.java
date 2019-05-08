
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
	private Map<String, SentimentAnalysisResult> senResultHM = new HashMap<String, SentimentAnalysisResult>();

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
	 * 
	 * @throws IOException
	 */
	private void readTxts() throws IOException {
		File txtFolder = new File("txt");
		for (File file : txtFolder.listFiles(new TxtFileFilter())) {

			ParserBaba parser = new ParserBaba(file.getPath());
			WordCounter counter = new WordCounter(file);
			
			// This prints the whole hashmap as it should
			System.out.println(counter.countOfWords());
			
			// This does print an empty hashmap
			System.out.println(counter.topWordCount(10));
			
			// Construct financial data object with data from parser and word counter's top 10 words
			FinancialData financialData = new FinancialData(parser.getCurrRevenue(), parser.getCurrNetIncome(),
					parser.getCurrAdjustedNetIncome(), parser.getFinancialYear(), parser.getFinQuarter(),
					parser.getCompanyName(), parser.getCompStatement(), counter.topWordCount(10));

			// Create unique key with company name, year, quarter
			String hmKey = financialData.getCompanyName() + "_" + financialData.getFinYear() + "_"
					+ financialData.getFinQuarter();

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
	 * The generateReports method calls the ReportGenerator class to produce a
	 * .csv and a .txt file summarizing the findings.
	 * 
	 * @throws FileNotFoundException
	 */
	public void generateReports() throws FileNotFoundException {
		ReportGenerator generator = new ReportGenerator(finDataHM, senResultHM);
		//TODO generator.generateCSV();
		generator.generateTxtReports();
	}

	/**
	 * getFinDataHM returns the value of the Map with the String key representing
	 * name, quarter, year and the FinancialData object representing the data
	 * retrieved after parsing the PDF files
	 * 
	 * @return
	 */
	public Map<String, FinancialData> getFinDataHM() {
		return finDataHM;
	}

	/**
	 * getFinDataHM returns the value of the Map with the String key representing
	 * name, quarter, year and the SentimentAnalysis object representing the data
	 * retrieved from sentiment analysis
	 * 
	 * @return
	 */
	public Map<String, SentimentAnalysisResult> getSenResultHM() {
		return senResultHM;
	}

	/**
	 * For testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Runner runner = new Runner();
			runner.generateReports();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
