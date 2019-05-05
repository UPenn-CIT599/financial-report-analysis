
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
/** The program utilize two external libraries PDF Box and SentimentAnalysis to read from PDFs 
 * and conduct sentiment analysis on financial statements
 * 
 * @author Cryrus
 *
 *Credit to  Ruthwik at 
 * https://github.com/Ruthwik/Sentiment-Analysis
 * 
 * Credit to RadixCode at
 * https://radixcode.com/pdfbox-example-code-how-to-extract-text-from-pdf-file-with-java
 * 
 * The idea is to loop through all the pdf files.
 * 1) 
 * 
 */
public class Runner {

	public static void main(String[] args) throws IOException {
		
		HashMap<String, FinancialData> finDataHM = new HashMap<String, FinancialData>();
		HashMap<String, SentimentAnalysisResult> senResultHM = new HashMap<String, SentimentAnalysisResult>();
		
		//Loop 1, loop through all pdfs in a folder
		File pdfFolder = new File("pdf");
		for (File file : pdfFolder.listFiles(new PDFFileFilter())) {
			
			PDFBoxReadFromFile PDFReader = new PDFBoxReadFromFile(file);
			PDFReader.printToTxt();  //Generate a txt file "filename_converted.txt" for use of DataParser
		}
		
		//TBC Loop2, for the same company, use the same parser to loop through all quarters
		File txtFolder = new File("txt");
		for (File file : txtFolder.listFiles(new TxtFileFilter())) {
			
			FinancialData financialData = new FinancialData();
			ParserBaba parser = new ParserBaba(file.getPath());
			
			financialData.setCompStatement(parser.parseCompStatement());	
			financialData.setCompanyName(parser.parseCompanyName());	
			financialData.setAdjustedNetIncome(parser.parseAdjustedNetIncome());
			financialData.setFinQuarter(parser.parseFinQuarter());
			financialData.setFinYear(parser.parseFinancialYear());
			financialData.setNetIncome(parser.parseNetIncome());
			financialData.setRevenue(parser.parseRevenue());
			
			//TBC Loop2 Close
		
			String hmKey = financialData.getCompanyName()+financialData.getFinYear()+financialData.getFinQuarter();

			finDataHM.put(hmKey, financialData);
			
			//Run the sentiment Analysis
			SentimentAnalysisResult sentimentAnalysis = new SentimentAnalysisResult(financialData.getCompStatement());
			senResultHM.put(hmKey, sentimentAnalysis);
		
		// End of the For Loop
		}
		
		
		}
}
