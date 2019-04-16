import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * author: @angelapwen
 * Credit to RadixCode at https://radixcode.com/pdfbox-example-code-how-to-extract-text-from-pdf-file-with-java
 */
public class PDFBoxReadFromFile {
    
    public static void main(String[] args) {
        PDFManager pdfManager = new PDFManager();
        pdfManager.setFilePath("201409.pdf");
        try {
            String text = pdfManager.toText();
            // System.out.println(text);
            // Print to file
            File out = new File("201409-text.txt");
            
            try (PrintWriter pw = new PrintWriter(out)) {
            	pw.print(text);
            	pw.flush();
            }
            catch (IOException e) {
            	e.printStackTrace();
            	System.out.println("Could not write out file.");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(PDFBoxReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}