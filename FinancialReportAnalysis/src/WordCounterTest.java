import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class WordCounterTest {

	@Test
	void test() {
		File f = new File("txt/BABA201409_converted.txt");
		WordCounter counter;
		try {
			counter = new WordCounter(f);
			Map<String, Integer> count = counter.topWordCount(10);
			assertTrue(count.containsKey("equity"));
			assertEquals((int)count.get("equity"), 12);
		} catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
}
