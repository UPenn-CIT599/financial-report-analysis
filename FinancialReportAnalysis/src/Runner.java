
import java.io.IOException;
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
 */
public class Runner {

	public static void main(String[] args) throws IOException {
		PDFBoxReadFromFile PDFReader = new PDFBoxReadFromFile("201409.pdf");
		PDFReader.printToTxt();
		
		//Temporary, suppose to take in a section of the PDF only
		//Need to build the DataParser and FinancialData class
		String text = PDFReader.pdfManager.toText();
		
		SentimentAnalysisResult SentimentAnalyzer = new SentimentAnalysisResult(text);
		SentimentAnalyzer.showSentimentScore();
		
		}
}
