import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class RunnerTest {

	@Test
	void test() throws IOException {
		HashMap<String, FinancialData> finDataHM = new HashMap<String, FinancialData>();
		HashMap<String, SentimentAnalysisResult> senResultHM = new HashMap<String, SentimentAnalysisResult>();
		
		//TBC Loop 1, loop through all pdfs in a folder
		
		//TBC Loop2, for the same company, use the same parser to loop through all quarters
		
		PDFBoxReadFromFile PDFReader = new PDFBoxReadFromFile("201409.pdf");
		PDFReader.printToTxt();  //Generate a txt file "filename_converted.txt" for use of DataParser
		
		ParserBaba parser = new ParserBaba(PDFReader.createTxtName());
		
		FinancialData financialData = new FinancialData();
		
		financialData.setAdjustedNetIncome(parser.parseAdjustedNetIncome());
		financialData.setCompanyName(parser.parseCompanyName());
		financialData.setCompStatement(parser.parseCompStatement());
		financialData.setFinQuarter(parser.parseFinQuarter());
		financialData.setFinYear(parser.parseFinancialYear());
		financialData.setNetIncome(parser.parseNetIncome());
		financialData.setRevenue(parser.parseRevenue());
		
		String hmKey = financialData.getCompanyName()+financialData.getFinYear()+financialData.getFinQuarter();

		finDataHM.put(hmKey, financialData);
		
		System.out.println(parser.parseCompanyName());
		
		//Run the sentiment Analysis
		SentimentAnalysisResult sentimentAnalysis = new SentimentAnalysisResult(financialData.getCompStatement());
	}

}
