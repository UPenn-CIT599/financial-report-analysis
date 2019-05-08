import java.io.*;
import java.util.*;

/**
 * a WordCounter class to grab a file, then count how many occurrences of each
 * word
 * 
 * @author Tim Culpan, Angela Wen, Cryrus Cheng
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
			String currentWord = inbound.next().toLowerCase(); //only count in lowercase form

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
	
	/** this method return the top N frequent words appeared in the wordCount HashMap
	 *  the method filter out common words like "a", "the", "period", and number or symbols
	 *  see method isCommonWord() for list of filered words
	 *  
	 * @param topN
	 * @return HashMap<String, Integer> topWordCount for the topN frequent word
	 */
	public Map<String, Integer> topWordCount(int topN) {
		Map<String, Integer> topWordCount = new HashMap<String, Integer>();
		ArrayList<String> topWords = new ArrayList<String>();
		int minWordCnt = 0;
		
		for (String candidateWord : wordCount.keySet()) {		
			// only execute if the candidate word is not a common word (a, the, an, etc)
			if (!isCommonWord(candidateWord)){ 
				//To initialize the topWord ArrayList
				if (topWords.isEmpty()) {
					topWords.add(candidateWord);
				} else {
					boolean replaceCnt = false;
					
					for (int x = 0 ; x < topWords.size() && replaceCnt == false ; x++) {
						if (wordCount.get(candidateWord) 
								> wordCount.get(topWords.get(x))) {
							topWords.add(x, candidateWord);	//insert at x, push all other elements to right				
							replaceCnt = true; //this will immediately exit the loop 
						}
					}
					
					//if replaceCnt remain false, that means the candidate word count is lower than
					//all elements of the topWords so it will be added to the end of ArrayList topWords
					if (replaceCnt == false) { 
						topWords.add(candidateWord);				
					}
					
					//Trim the ArrayList topWords if exceed TopN
					if(topWords.size() > topN) {
						topWords.remove(topN);
					}
				} //end of if statement
			}	
		} //end of for loop
		
		//We now have an ArrayList of the topN common words
		//Just need to put them in the HashMap for output
		for (int x = 0 ; x < topWords.size() ; x++) {
			topWordCount.put(topWords.get(x), wordCount.get(topWords.get(x)));
		}
	return topWordCount;		
	}
	
	
	/** Checks if a word is a common word e.g. a, the, an, or pure number or symbols
	 * 
	 * @param word
	 * @return
	 */
	private static boolean isCommonWord(String word) {
		ArrayList<String> commonWord = new ArrayList<String>();
		commonWord.add("a");
		commonWord.add("an");
		commonWord.add("the");
		commonWord.add("those");
		commonWord.add("that");
		commonWord.add("september");
		commonWord.add("december");
		commonWord.add("march");
		commonWord.add("june");
		commonWord.add("million");		
		commonWord.add("thousand");
		commonWord.add("billion");
		commonWord.add("dollar");
		commonWord.add("in");
		commonWord.add("and");
		commonWord.add("of");
		commonWord.add("is");
		commonWord.add("was");
		commonWord.add("to");
		commonWord.add("quarter");
		commonWord.add("30");
		commonWord.add("31");
		commonWord.add("same");
		commonWord.add("-");
		commonWord.add("ended");
		commonWord.add("increase");
		commonWord.add("decrease");
		commonWord.add("our");
		commonWord.add("compared");
		commonWord.add("income");
		commonWord.add("from");
		commonWord.add("net");
		commonWord.add("on");
		commonWord.add("revenue");
		commonWord.add("diluted");
		commonWord.add("_");
		commonWord.add("or");
		commonWord.add("by");
		commonWord.add("due");
		commonWord.add("expense");
		commonWord.add("commerce");
		commonWord.add("with");
		commonWord.add("gmv");
		commonWord.add("rmb");
		commonWord.add("were");
		commonWord.add("eps");
		commonWord.add("ucweb");
		commonWord.add("end");
		commonWord.add("period");
		commonWord.add("result");
		commonWord.add("new");
		commonWord.add("which");
		commonWord.add("twelve");
		commonWord.add("respective");
		commonWord.add("such");
		commonWord.add("us$");
		commonWord.add("be");
		commonWord.add("its");

		//Regex check for number / symbols 
		if (word.matches("\\d*.")) return true;
		if (word.matches(".\\d.*")) return true;
		if (word.matches("\\W.*")) return true;


		//check for common words
		for (String str : commonWord) {
			if (word.contains(str)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		File f = new File("dataset/Alibaba_2014_9.txt");
		WordCounter count;
		try {
			count = new WordCounter(f);
			System.out.println(count.countOfWords());
			System.out.println("Top 10 words are ");
			System.out.println(count.topWordCount(10));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}// END WordCounter Class