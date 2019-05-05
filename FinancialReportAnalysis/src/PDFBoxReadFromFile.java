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
	private PDFManager pdfManager = new PDFManager();
	private String fileName;
	public static final String outputFolder = "txt";

	/**
	 * PDFBoxReadFromFile constructor sets the fileName input.
	 * @param fileName
	 * @throws IOException
	 */
	public PDFBoxReadFromFile(String fileName) throws IOException {
		this.fileName = fileName;
		pdfManager.setFilePath(fileName); // Constructs PDF Manager with correct file name.
	}
	
	/**
	 * getString returns the String output of the PDF
	 * @return the String output of the PDF
	 * @throws IOException
	 */
	public String getString() throws IOException {
		return pdfManager.toText();
	}
	
	/**
	 * createTxtName removes the .pdf extension of the PDF being read and
	 * returns the .txt name of the file to write to
	 * @return the .txt name of the file to write to
	 */
	public String createTxtName() {
		// Assume input file ends in .pdf
		
		// Get substring of PDF file without .pdf
		String outputFileName = fileName.substring(fileName.lastIndexOf("/")+1, fileName.lastIndexOf("."));
		
		// Append _converted.txt
		outputFileName += "_converted.txt";
		
		return outputFileName;
	}

	/**
	 * printToTxt writes the String output of the PDF to the new .txt file
	 * @throws IOException
	 */
	public void printToTxt() throws IOException {
		String text = pdfManager.toText();// Retrieves String from PDF Manager

		//create a directory called txt
		File folder = new File(outputFolder);
		folder.mkdir();
				
		// Print to file
		File out = new File(folder, createTxtName());

		PrintWriter pw = new PrintWriter(out);
		pw.print(text);
		pw.flush();
		pw.close();
	}

	public static void main(String[] args) {
		try {
    		PDFBoxReadFromFile PDFReader = new PDFBoxReadFromFile("BIDU201503.pdf");
    		PDFReader.printToTxt();
    		
    		//read out tells us it's done.
    		
    		System.out.println("PDF Read complete.");
    		System.out.println("Please REFRESH Eclipse and look for the file: " + PDFReader.createTxtName() );
    		
    		
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}