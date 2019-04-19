import java.io.IOException;
import java.util.*;

/**
 * a WordCounter class to grab a file, then count how many occurrences of each
 * word
 * 
 * @author Tim Culpan
 * 
 */


 
//

public static void main(String[] args) {
	try {
		PDFBoxReadFromFile PDFReader = new PDFBoxReadFromFile("201409.pdf");
		PDFReader.printToTxt();
	//
		
	}//end try
	catch (IOException e) {
		e.printStackTrace();
	}//end catch
}//end main	

//


public class WordCounter {

	HashMap<String, Integer> wordCount = new HashMap<>();
	String incoming;
	Scanner input = new Scanner(incoming); 
	String currentWord;

	/**
	 * 
	 * @param filename the incoming file we want to analyze
	 * @return HashMap of each word and how many times it occurs
	 * 
	 */		

	public HashMap<String, Integer> countOfWords(String filename) {
		incoming = filename;

		while (input.hasNext()) {
			String currentWord = input.next();

			if (wordCount.containsKey(currentWord)) {
				wordCount.put(currentWord, wordCount.get(currentWord) + 1);
			} // END has loop

			if (!wordCount.containsKey(currentWord)) {
				wordCount.put(currentWord, 1);
			} // END ! loop

		} // END WHILE

		return wordCount;		
		
	}// END countOfWords method
	

}// END WordCounter Class