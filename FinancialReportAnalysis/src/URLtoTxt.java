
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

	/**
	 * URLtoTxt constructor saves the correct URL to the class
	 * 
	 * @param url
	 */
	public URLtoTxt(String url) {
		this.urlToOpen = url;
	}

	/**
	 * readPDF opens the URl and PDF Document contained and returns the String
	 * content of the PDF
	 * 
	 * @return The String content of PDF in the URL
	 * @throws IOException
	 */
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

	/**
	 * createTxtName removes the .pdf extension of the URL being read and returns
	 * the .txt name of the file to write to
	 * 
	 * @return the .txt name of the file to write to
	 */
	private String createTxtName() {
		// Assume input URL ends in .pdf

		// Get substring of PDF file without .pdf
		String outputFileName = urlToOpen.substring(urlToOpen.indexOf(".") + 1, urlToOpen.lastIndexOf("."));

		// Append _converted.txt
		outputFileName += "_converted.txt";

		// Remove all slashes from name
		outputFileName = outputFileName.replaceAll("/", "");

		return outputFileName;
	}

	/**
	 * printToTxt writes the String output of the PDF to the new .txt file
	 * 
	 * @throws IOException
	 */
	public void printToTxt() throws IOException {
		String text = readPDF();// Retrieves String from PDF Manager

		// create a directory called txt
		File folder = new File("txt");
		folder.mkdir();

		// Print to file
		File out = new File(folder, createTxtName());

		PrintWriter pw = new PrintWriter(out);
		pw.print(text);
		pw.flush();
		pw.close();
	}

}
