import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class WordCounterTest {

	@Test
	void test() {
		File f = new File("txt/BABA201409_converted.txt");
		WordCounter counter;
		try {
			counter = new WordCounter(f);
			HashMap<String, Integer> count = counter.countOfWords();
			assertTrue(count.containsKey("RMB5,893"));
			assertEquals((double)count.get("RMB5,893"), 2);
		} catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
