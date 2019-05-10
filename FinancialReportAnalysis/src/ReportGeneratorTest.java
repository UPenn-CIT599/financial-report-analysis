import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ReportGeneratorTest {

	File f;
	DataParser parser;
	WordCounter counter;
	Map<String, FinancialData> finDataHM = new HashMap<String, FinancialData>();
	Map<String, SentimentAnalysisResult> senResultHM = new HashMap<String, SentimentAnalysisResult>();

	// Construct financial data hashmap and sentiment analysis hashmap for one file
	ReportGeneratorTest() {
		f = new File("txt/BABA201412_converted.txt");
		parser = new ParserBaba(f.getPath());

		try {
			counter = new WordCounter(f);
			FinancialData financialData = new FinancialData(parser.getCurrRevenue(), parser.getCurrNetIncome(),
					parser.getCurrAdjustedNetIncome(), parser.getFinancialYear(), parser.getFinQuarter(),
					parser.getCompanyName(), parser.getCompStatement(), counter.topWordCount(10));

			String hmKey = financialData.getCompanyName() + "_" + financialData.getFinYear() + "_"
					+ financialData.getFinQuarter();

			finDataHM.put(hmKey, financialData);

			SentimentAnalysisResult sentimentAnalysis = new SentimentAnalysisResult(financialData.getCompStatement());

			senResultHM.put(hmKey, sentimentAnalysis);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void test() {
		try {
			ReportGenerator newReport = new ReportGenerator(finDataHM, senResultHM);
			newReport.generateTxtReports();
			newReport.generateCSV();
			newReport.generateWordCountCSVs();

			File txtFile = new File("dataset/Alibaba_2014_12.txt");
			assertTrue(txtFile.exists());

			File csvFile = new File("dataset/Alibaba_2014_12_WordCount.csv");
			assertTrue(csvFile.exists());

			File allData = new File("dataset/allData.csv");
			assertTrue(allData.exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
