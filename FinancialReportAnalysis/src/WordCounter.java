import java.io.*;
import java.util.*;

/**
 * a WordCounter class to grab a file, then count how many occurrences of each
 * word. It also has a method to create a hashmap of the topN meaningful words.
 * 
 * @author Tim Culpan, Angela Wen, Cryrus Cheng
 * 
 */

public class WordCounter {
	File f;
	HashMap<String, Integer> wordCount = new HashMap<>();

	/**
	 * The constructor initializes the file to be read and immediately calls the
	 * countOfWords method to fill the hashmap with all words and counts.
	 * 
	 * @param File f
	 * @throws IOException
	 */

	public WordCounter(File f) throws IOException {
		this.f = f;
		countOfWords();
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
			String currentWord = inbound.next().toLowerCase(); // only count in lowercase form

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

	/**
	 * this method return the top N frequent words appeared in the wordCount HashMap
	 * the method filter out common words like "a", "the", "period", and number or
	 * symbol. See method isCommonWord() for list of filtered words
	 * 
	 * @param topN the limit to how many most-frequent words we wish to return
	 * @return HashMap<String, Integer> topWordCount for the topN frequent word
	 */
	public Map<String, Integer> topWordCount(int topN) {
		Map<String, Integer> topWordCount = new HashMap<String, Integer>();
		ArrayList<String> topWords = new ArrayList<String>();

		for (String candidateWord : wordCount.keySet()) {
			// only execute if the candidate word is not a common word (a, the, an, etc)
			if (!isCommonWord(candidateWord)) {
				// To initialize the topWord ArrayList
				if (topWords.isEmpty()) {
					topWords.add(candidateWord);
				} else {
					boolean replaceCnt = false;

					for (int x = 0; x < topWords.size() && replaceCnt == false; x++) {
						if (wordCount.get(candidateWord) > wordCount.get(topWords.get(x))) {
							topWords.add(x, candidateWord); // insert at x, push all other elements to right
							replaceCnt = true; // this will immediately exit the loop
						}
					}

					// if replaceCnt remain false, that means the candidate word count is lower than
					// all elements of the topWords so it will be added to the end of ArrayList
					// topWords
					if (replaceCnt == false) {
						topWords.add(candidateWord);
					}

					// Trim the ArrayList topWords if exceed TopN
					if (topWords.size() > topN) {
						topWords.remove(topN);
					}
				} // end of if statement
			}
		} // end of for loop

		// We now have an ArrayList of the topN common words
		// Just need to put them in the HashMap for output
		for (int x = 0; x < topWords.size(); x++) {
			topWordCount.put(topWords.get(x), wordCount.get(topWords.get(x)));
		}
		return topWordCount;
	}

	/**
	 * Checks if a word is a common word e.g. a, the, an, or pure number or symbols
	 * This is hard-coded. A more resource-intense approach would allow us
	 * to manually add words which we wish to exclude from our topN word list
	 * 
	 * @param word
	 * @return
	 */
	private static boolean isCommonWord(String word) {
		ArrayList<String> commonWord = new ArrayList<String>(Arrays.asList("null", "a", "an", "the", "those", "that",
				"september", "december", "march", "june", "million", "thousand", "billion", "dollar", "in", "and", "of",
				"is", "was", "to", "quarter", "30", "31", "same", "-", "ended", "increase", "decrease", "compared",
				"income", "from", "net", "on", "revenue", "diluted", "_", "or", "by", "due", "expense", "at", "with",
				"gmv", "rmb", "were", "eps", "end", "period", "result", "new", "which", "twelve", "respective", "us$",
				"be", "its", "per"));

		// Regex check for number / symbols
		if (word.matches("\\d*."))
			return true;
		if (word.matches(".\\d.*"))
			return true;
		if (word.matches("\\W.*"))
			return true;

		// check for common words
		for (String str : commonWord) {
			if (word.contains(str)) {
				return true;
			}
		}
		return false;
	}

}// END WordCounter Class