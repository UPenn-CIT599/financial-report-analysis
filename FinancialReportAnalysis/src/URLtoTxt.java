/**
 * This class allows the user to access a PDF from a URL and access a .txt file
 * with the contents of the PDF.
 * 
 * @author: angelapwen
 * Credit to Naveen AutomationLabs
 */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class URLtoTxt {
	
	String urlToOpen;
	
	public URLtoTxt(String url) {
		this.urlToOpen = url;
	}

	public String readPDF() throws IOException {
		URL url = new URL(urlToOpen);

		InputStream is = url.openStream();
		BufferedInputStream fileParse = new BufferedInputStream(is);
		PDDocument document = null;

		document = PDDocument.load(fileParse);
		String pdfContent = new PDFTextStripper().getText(document);
		document.close();
		return pdfContent;
	}

	public static void main(String[] args) {
		URLtoTxt reader = new URLtoTxt("https://www.alibabagroup.com/en/news/press_pdf/p190130.pdf");

		// Print to file
		File out = new File("alibaba-statement.txt");

		try (PrintWriter pw = new PrintWriter(out)) {
			try {
				pw.print(reader.readPDF());
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Could not write out file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
