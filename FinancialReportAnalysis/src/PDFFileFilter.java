
import java.io.File;
import java.io.FileFilter;
/**This is a FileFilter for use with the method listFiles()
 * Filtering pdf files only.
 * It overrides the builtin accept() method
 * 
 * @author Cryrus
 *
 */
public class PDFFileFilter implements FileFilter{

	@Override
	public boolean accept(File pathname) {
			return pathname.getName().toLowerCase().endsWith(".pdf");

	}
}
