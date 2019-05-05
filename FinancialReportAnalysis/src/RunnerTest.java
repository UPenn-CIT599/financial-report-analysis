import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class RunnerTest {

	@Test
	public static void main(String[] args) throws IOException {
		
		HashMap<String, FinancialData> finDataHM = new HashMap<String, FinancialData>();
		HashMap<String, SentimentAnalysisResult> senResultHM = new HashMap<String, SentimentAnalysisResult>();
		
		//Loop 1, loop through all pdfs in a folder
		File folder = new File("/Users/Cryrus/OneDrive - PennO365/GitHub/final-project-financial-reporting-analysis/FinancialReportAnalysis/");
		for (File file : folder.listFiles(new PDFFileFilter())) {
			System.out.println("file: " + file.getName());
			
		}
		
		//TBC Loop2, for the same company, use the same parser to loop through all quarters
		
		PDFBoxReadFromFile PDFReader = new PDFBoxReadFromFile("201409.pdf");
		PDFReader.printToTxt();  //Generate a txt file "filename_converted.txt" for use of DataParser
		
		ParserBaba parser = new ParserBaba(PDFReader.createTxtName());
		
		FinancialData financialData = new FinancialData();
		
//		financialData.setAdjustedNetIncome(parser.parseAdjustedNetIncome());
		financialData.setCompanyName(parser.parseCompanyName());
		financialData.setCompStatement(parser.parseCompStatement());
		financialData.setFinQuarter(parser.parseFinQuarter());
		financialData.setFinYear(parser.parseFinancialYear());
//		financialData.setNetIncome(parser.parseNetIncome());
//		financialData.setRevenue(parser.parseRevenue());
		
		String hmKey = financialData.getCompanyName()+financialData.getFinYear()+financialData.getFinQuarter();

		finDataHM.put(hmKey, financialData);
		
		//Run the sentiment Analysis
		SentimentAnalysisResult sentimentAnalysis = new SentimentAnalysisResult(financialData.getCompStatement());
		
		senResultHM.put(hmKey, sentimentAnalysis);
	
		}
}
