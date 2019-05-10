import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class URLtoTxtTest {

	/**
	 * Tests to see whether the String output from a URL with a PDF
	 * contains the correct company and quarter
	 * @author angelapwen
	 */
	@Test
	void testReadPDF() {
		URLtoTxt test = new URLtoTxt("https://www.alibabagroup.com/en/news/press_pdf/p190130.pdf");
		try {
			assertTrue(test.readPDF().contains("Alibaba Group") && test.readPDF().contains("December 31, 2018"));
			
			test.printToTxt();
			File f = new File("txt/alibabagroup.comennewspress_pdfp190130_converted.txt");
			assertTrue(f.exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
