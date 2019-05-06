import java.io.File;
import java.io.FileFilter;

public class TxtFileFilter implements FileFilter{

	@Override
	public boolean accept(File pathname) {
			return pathname.getName().toLowerCase().endsWith(".txt");

	}

}
