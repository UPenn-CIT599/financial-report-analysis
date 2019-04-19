import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class PDFBoxReadFromFileTest {

	@Test
	void test() {
		try {
    		PDFBoxReadFromFile PDFReader = new PDFBoxReadFromFile("201409.pdf");
    		assertTrue(PDFReader.getString().contains("Alibaba Group") && 
    				PDFReader.getString().contains("September 30, 2014"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
