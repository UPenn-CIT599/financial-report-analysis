
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
		
		//Loop 1, loop through all pdfs in a folder and export to txt files in folder "txt"
		File pdfFolder = new File("pdf");
		for (File file : pdfFolder.listFiles(new PDFFileFilter())) {
			
			PDFBoxReadFromFile PDFReader = new PDFBoxReadFromFile(file);
			PDFReader.printToTxt();  //Generate a txt file "filename_converted.txt" for use of DataParser
		}
		
		//Loop2, for the same company, use the same parser to loop through all quarters
		File txtFolder = new File("txt");
		for (File file : txtFolder.listFiles(new TxtFileFilter())) {
			
			FinancialData financialData = new FinancialData();
			ParserBaba parser = new ParserBaba(file.getPath());
			
			financialData.setCompStatement(parser.parseCompStatement());	
			financialData.setCompanyName(parser.parseCompanyName());	
			financialData.setAdjustedNetIncome(parser.parseAdjustedNetIncome());
			financialData.setFinQuarter(parser.parseFinQuarter());
			parser.parseYearQuarterCompany();
			financialData.setFinYear(parser.getYear());
			financialData.setFinQuarter(parser.getQuarter());
			financialData.setCompanyName(parser.getCompanyName());
			financialData.setNetIncome(parser.parseNetIncome());
			financialData.setRevenue(parser.parseRevenue());
		
			String hmKey = financialData.getCompanyName()+financialData.getFinYear()+financialData.getFinQuarter();

			finDataHM.put(hmKey, financialData);
			
			//Run the sentiment Analysis
			SentimentAnalysisResult sentimentAnalysis = new SentimentAnalysisResult(financialData.getCompStatement());
			sentimentAnalysis.showSentimentScore();
			senResultHM.put(hmKey, sentimentAnalysis);
		
		// End of the For Loop
		}
		
		//Loop3 outputing the two HashMaps
		for (String key : finDataHM.keySet()) {
			//create folder dataset
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
}
