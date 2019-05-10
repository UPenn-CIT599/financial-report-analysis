
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * NOTE: Please read the README first. This code requires the installation of
 * FOUR external components: PDFBox; SentimentAnalysis; Stanford NLP for
 * Sentiment Analysis; ApacheCommons CSV
 * 
 * 
 * We envision that this might take about 10-15mins, depending on download
 * speed. So open the README, click on the various downloads, grab a cup (of
 * Java, of course) and come right back for the ride of your life
 * 
 * oh. and remember to click REFRESH when you have downloaded and installed
 * everything.
 * 
 * This here RUNNER Class ties together all the various components of the
 * project As implemented here, it's processing and analysing a limited number of
 * documents. 
 * This is just to give you a taste of what it can do because it's quite CPU & memory intensive
 * 
 * 
 * @author Cryrus Cheng, Angela Wen, Tim Culpan
 *
 *         Credit to Ruthwik at https://github.com/Ruthwik/Sentiment-Analysis *
 *         Credit to RadixCode at
 *         https://radixcode.com/pdfbox-example-code-how-to-extract-text-from-pdf-file-with-java
 * 
 */
public class Runner {

	private Map<String, FinancialData> finDataHM = new HashMap<String, FinancialData>();
	private Map<String, SentimentAnalysisResult> senResultHM = new HashMap<String, SentimentAnalysisResult>();
	private String[] URLs;

	/**
	 * The Runner constructor loops through all PDFs from a list of URLs and exports
	 * them to .txt. Then it fills the instance variable hashmaps.
	 */
	public Runner() throws IOException {
		// Initialize all URLs of PDF files to read
		URLs = new String[] { "https://www.alibabagroup.com/en/news/press_pdf/p141104.pdf", // BABA201409
				"https://www.alibabagroup.com/en/news/press_pdf/p150129.pdf", // BABA201412
				"https://www.alibabagroup.com/en/news/press_pdf/p160128.pdf", // BABA201512
				"https://www.alibabagroup.com/en/news/press_pdf/p170124.pdf", // BABA201612
				"https://www.alibabagroup.com/en/news/press_pdf/p180201.pdf", // BABA201712
				"https://www.alibabagroup.com/en/news/press_pdf/p190130.pdf" // BABA201812
		};

		// Loop through all PDFs in URL and export to .txt files
		convertURLToTxt();

		// Loop through all txt files to fill hashmaps
		readTxts();

	}

	/**
	 * convertURLToTxt uses the URLtoTxt class to convert PDF files from URLs into
	 * txt files within the "txt" folder.
	 * 
	 * @throws IOException
	 */
	private void convertURLToTxt() throws IOException {
		for (String URL : URLs) {
			URLtoTxt converter = new URLtoTxt(URL);
			converter.printToTxt();
		}
	}

	/**
	 * PDFtoTxt opens all PDFs within the "pdf" folder and creates a txt file with
	 * PDFBoxReadFromFile.
	 * 
	 * Currently this method does not need to be used because of the use of
	 * convertURLToTxt() which allows to read PDF directly from URL.
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

			DataParser parser = new ParserBaba(file.getPath());
			WordCounter counter = new WordCounter(file);

			// Print status update for user on console
			System.out.println(
					"Parsing and running analysis on " + parser.getCompanyName() + "\'s" + " statement from year "
							+ parser.getFinancialYear() + " in quarter " + parser.getFinQuarter() + "...");

			// For testing
			System.out.println(parser.getCompStatement());

			// Construct financial data object with data from parser and word counter's top
			// 10 words
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
			senResultHM.put(hmKey, sentimentAnalysis);

			// End of the For Loop
		}
	}

	/**
	 * The generateReports method calls the ReportGenerator class to produce a .csv
	 * and .txt and .csv files for each PDF summarizing the findings.
	 * 
	 * @throws IOException
	 */
	public void generateReports() throws IOException {
		ReportGenerator generator = new ReportGenerator(finDataHM, senResultHM);
		generator.generateCSV();
		generator.generateWordCountCSVs();
	}

	/**
	 * printCompletion prints a message to console to let the user know that the
	 * parsing and analyses have completed.
	 */
	public void printCompletion() {
		System.out.println("=====================================================");
		System.out.println("Parsing and analysis complete!");
		System.out.println("Please check the /dataset folder for a .csv file");
		System.out.println("containing financial data and sentiment analysis");
		System.out.println("data; .txt files for each PDF containing the comp");
		System.out.println("statement and most common words; and .csv files for");
		System.out.println("each PDF with the top 10 most common words.");
		System.out.println("=====================================================");
	}

	/**
	 * getFinDataHM returns the value of the Map with the String key representing
	 * name, quarter, year and the FinancialData object representing the data
	 * retrieved after parsing the PDF files
	 * 
	 * @return finDataHM hashmap
	 */
	public Map<String, FinancialData> getFinDataHM() {
		return finDataHM;
	}

	/**
	 * getFinDataHM returns the value of the Map with the String key representing
	 * name, quarter, year and the SentimentAnalysis object representing the data
	 * retrieved from sentiment analysis
	 * 
	 * @return senResultHM hashmap
	 */
	public Map<String, SentimentAnalysisResult> getSenResultHM() {
		return senResultHM;
	}

	/**
	 * This is the main method that will be called when the program is run.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Runner runner = new Runner();
			runner.generateReports();
			runner.printCompletion();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
