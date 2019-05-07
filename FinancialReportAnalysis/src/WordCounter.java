import java.io.*;
import java.util.*;

/**
 * a WordCounter class to grab a file, then count how many occurrences of each
 * word
 * 
 * @author Tim Culpan, Angela Wen
 * 
 */

public class WordCounter {
	File f;
	HashMap<String, Integer> wordCount = new HashMap<>();
	WordCounter incoming;

	String currentWord;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param File f
	 * @throws IOException
	 */

	public WordCounter(File f) throws IOException {
		this.f = f;
	}

	/**
	 * This method creates a HashMap of words and no. of occurrences
	 * 
	 * @return HashMap (word, occurrences)
	 * @throws FileNotFoundException
	 * 
	 */

	public HashMap<String, Integer> countOfWords() throws FileNotFoundException {
		Scanner inbound = new Scanner(f);

		while (inbound.hasNext()) {
			String currentWord = inbound.next();

			if (wordCount.containsKey(currentWord)) {
				wordCount.put(currentWord, wordCount.get(currentWord) + 1);
			} // END has loop

			else if (!wordCount.containsKey(currentWord)) {
				wordCount.put(currentWord, 1);
			} // END ! loop

		} // END WHILE
		inbound.close();
		return wordCount;

	}// END countOfWords method

}// END WordCounter Class