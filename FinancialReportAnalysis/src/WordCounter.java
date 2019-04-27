//Apple
import java.io.*;
import java.util.*;

//25 April 2019: 18:56
//Note error Line 40: The constructor Scanner(FileReader) is undefined

/**
 * a WordCounter class to grab a file, then count how many occurrences of each word
 *  
 * @author Tim Culpan
 * 
 */

public class WordCounter {
	String filename;
	HashMap<String, Integer> wordCount = new HashMap<>();
	WordCounter incoming;
	
	String currentWord;
	 
	/** CONSTRUCTOR 
	 * @param fileName
	 *  @throws IOException
	 */	
	
	public WordCounter (String filename) throws IOException {
		this.filename = filename;
	}

	/**
	 * This method creates a HashMap of words and no. of occurrences
	 * 
	 * @param filename the incoming file we want to analyze
	 * @return HashMap (word, occurrences)
	 * 
	 */		

	public HashMap<String, Integer> countOfWords(String filename) {
		this.filename = filename;
		FileReader countThis = new FileReader(filename);
		ArrayList<String> fileAsArray = countThis.getfileReadIn();
				
		Scanner inbound = new Scanner(countThis.getfileName()); 

		while (inbound.hasNext()) {
			String currentWord = inbound.next(); 

			if (wordCount.containsKey(currentWord)) {
				wordCount.put(currentWord, wordCount.get(currentWord) + 1);
			} // END has loop

			if (!wordCount.containsKey(currentWord)) {
				wordCount.put(currentWord, 1);
			} // END ! loop

		} // END WHILE

		return wordCount;		
		
	}// END countOfWords method
	
	
	
	//This main method is just to implement the WordCounter.
	// can be removed and folded into our Runner class
//	public static void main (String[] args) {
//		
//			try {
//				HashMap<String, Integer> count = new HashMap<>();
//				
//				// Actually, I think we don't need this
//				WordCounter counter = new WordCounter("201409_converted.txt");
//				
//				count = counter.countOfWords("201409_converted.txt");
//					    		
//	    		System.out.println(count);
//	    		
//	    		//
//	    	
//			}
//			catch (IOException e) {
//				e.printStackTrace();
//			}
//			 
//	 }


}// END WordCounter Class