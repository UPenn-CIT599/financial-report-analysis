import java.util.*;


/**
 * a WordCounter class to grab a file, then count how many occurrences of each word
 * @author Tim Culpan
 * 
 * work in progress 
 */

public class WordCounter {
	
	HashMap<String, Integer> wordCount = new HashMap<>();
	ArrayList<String> incoming = new ArrayList<>();
	Scanner input = new Scanner(incoming); //ERROR: The constructor Scanner(ArrayList<String>) is undefined 
	String currentWord;
	
	/**
	 * 
	 * @param 	listToCount the incoming ArrayList we want to analyze
	 * @return 	HashMap of each word and how many times it occurs
	 * 
	 */
	
	public HashMap<String, Integer> countOfWords(ArrayList<String> listToCount) {
		incoming = listToCount;
		
		while (input.hasNext()) {
			String currentWord = input.next();
		
			if (wordCount.containsKey(currentWord)) {
				wordCount.put(currentWord, wordCount.get(currentWord)+1);
			}//END has loop
			
			if (!wordCount.containsKey(currentWord)) {
				wordCount.put(currentWord, 1);
			}//END ! loop
			
		}//END WHILE
		
		return wordCount;
	
		
	}//END countOfWords method
	
}//END WordCounter Class
	
