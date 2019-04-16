import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PDFBoxReadFromFile collaborates with PDFManager to read a PDF file and 
 * outputs the contents to a .txt file.
 * @author: angelapwen Credit to RadixCode at
 *          https://radixcode.com/pdfbox-example-code-how-to-extract-text-from-pdf-file-with-java
 */
public class PDFBoxReadFromFile {
	PDFManager pdfManager = new PDFManager();

	public PDFBoxReadFromFile(String fileName) throws IOException {
		pdfManager.setFilePath(fileName); // Constructs PDF Manager with correct file name.
	}
	
	public String getString() throws IOException {
		return pdfManager.toText();
	}

	public void printToTxt() throws IOException {
		String text = pdfManager.toText();// Retrieves String from PDF Manager

		// Print to file
		File out = new File("201409-text.txt");

		PrintWriter pw = new PrintWriter(out);
		pw.print(text);
		pw.flush();
		pw.close();
	}

	public static void main(String[] args) {
		try {
    		PDFBoxReadFromFile PDFReader = new PDFBoxReadFromFile("201409.pdf");
    		PDFReader.printToTxt();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}