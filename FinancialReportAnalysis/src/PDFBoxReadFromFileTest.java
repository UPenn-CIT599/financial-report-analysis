import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class PDFBoxReadFromFileTest {

	@Test
	void test() {
		try {
			File f = new File("pdf/BABA201409.pdf");
			PDFBoxReadFromFile PDFReader = new PDFBoxReadFromFile(f);
			assertTrue(PDFReader.getString().contains("Alibaba Group")
					&& PDFReader.getString().contains("September 30, 2014"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
