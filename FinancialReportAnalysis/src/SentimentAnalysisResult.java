import com.stanford_nlp.SentimentAnalyzer.SentimentAnalyzer;
import com.stanford_nlp.model.SentimentResult;

/**
 * The SentimentAnalysisRunner interprets a string and export sentiment score on
 * 5 classes Very positive, Positive, Neutral, Negative, Very Negative
 * 
 * 
 * @author Cryrus
 *
 *         Credit to Ruthwik at https://github.com/Ruthwik/Sentiment-Analysis
 *
 *         The original program of Ruthwik was using Stanford CoreNLP v3.8.0 but
 *         it is no longer available for download at Stanford website so I
 *         updated the reference to the latest version Stanford CoreNLP v3.9.2
 *         and export Ruthwik program as SentimentAnalysis.jar to be used in
 *         this project, FinancialReportAnalysis
 * 
 */
public class SentimentAnalysisResult {
	private double sentimentScore;
	private String sentimentType;
	private double percentVeryPositive;
	private double percentPositive;
	private double percentNeutral;
	private double percentNegative;
	private double percentVeryNegative;

	public SentimentAnalysisResult(String text) {
		SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
		sentimentAnalyzer.initialize();
		SentimentResult sentimentResult = sentimentAnalyzer.getSentimentResult(text);

		sentimentScore = sentimentResult.getSentimentScore();
		sentimentType = sentimentResult.getSentimentType();
		percentVeryPositive = sentimentResult.getSentimentClass().getVeryPositive();
		percentPositive = sentimentResult.getSentimentClass().getPositive();
		percentNeutral = sentimentResult.getSentimentClass().getNeutral();
		percentNegative = sentimentResult.getSentimentClass().getNegative();
		percentVeryNegative = sentimentResult.getSentimentClass().getVeryNegative();
	}

	// Accessor methods below

	public double getSentimentScore() {
		return sentimentScore;
	}

	public String getSentimentType() {
		return sentimentType;
	}

	public double getVeryPositive() {
		return percentVeryPositive;
	}

	public double getPositive() {
		return percentPositive;
	}

	public double getNeutral() {
		return percentNeutral;
	}

	public double getNegative() {
		return percentNegative;
	}

	public double getVeryNegative() {
		return percentVeryNegative;
	}

	/**
	 * The toString method prints the appropriate values with comma separated
	 * delineators to print to CSV file
	 */
	@Override
	public String toString() {
		return sentimentScore + "," + sentimentType + "," + percentVeryPositive + "," + percentPositive + ","
				+ percentNeutral + "," + percentNegative + "," + percentVeryNegative;
	}

}
