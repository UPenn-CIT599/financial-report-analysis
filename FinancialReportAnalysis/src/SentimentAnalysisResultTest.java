import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SentimentAnalysisResultTest {

	@Test
	void test() {
		String text = "Hangzhou, China, November 4, 2014 – Alibaba Group Holding Limited (NYSE: BABA) today announced \n" + 
				"its financial results for the quarter ended September 30, 2014.  \n" + 
				"“We delivered a strong quarter with significant growth across our key operating metrics,” said Jonathan Lu, \n" + 
				"chief executive officer of Alibaba Group. “Our business continues to perform well, and our results reflect \n" + 
				"both the strength of our ecosystem and the strong foundation we have for sustainable growth.  On our China \n" + 
				"retail marketplaces, gross merchandise volume for the quarter increased 49% and annual active buyers \n" + 
				"increased 52% year on year.  We extended our unrivaled leadership in mobile with 217 million monthly \n" + 
				"active users on our mobile commerce apps in September and US$95 billion in mobile GMV for the twelve \n" + 
				"months ended September 2014. We are also encouraged by continued improvement of mobile monetization \n" + 
				"which demonstrates the strong commercial intent of our users.” \n" + 
				" “Our financial performance this quarter was robust, with revenue growing 54% year on year.” said Maggie \n" + 
				"Wei Wu, chief financial officer of Alibaba Group. “We continue to execute our focused growth strategy, and \n" + 
				"the fundamental strength of our business gives us the confidence to invest in new initiatives to add new users, \n" + 
				"improving engagement and customer experience, expand our products and services and drive long-term \n" + 
				"shareholder value.”  \n" + 
				"";
		
		SentimentAnalysisResult SentimentAnalyzer = new SentimentAnalysisResult(text);
		SentimentAnalyzer.showSentimentScore();
		
	}

}
