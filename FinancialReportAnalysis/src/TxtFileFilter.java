import java.io.File;
import java.io.FileFilter;
/**This is a FileFilter for use with the method listFiles()
 * Filtering txt files only.
 * It overrides the builtin accept() method
 *
 * 
 * @author Cryrus
 *
 */
public class TxtFileFilter implements FileFilter{

	@Override
	public boolean accept(File pathname) {
			return pathname.getName().toLowerCase().endsWith(".txt");

	}

}
