import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class RunnerTest {
	
	@Test
	void test() {
		try {
			Runner test = new Runner();
			assertTrue(test.getFinDataHM().containsKey("Alibaba_2014_9"));
			assertTrue(test.getSenResultHM().containsKey("Alibaba_2014_9"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
