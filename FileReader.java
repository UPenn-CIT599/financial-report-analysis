import java.io.*;
import java.util.*;
/**
 * A basic file reader Class
 * reads a file (fileName)
 * creates an ArrayList<String> (fileReadIn)
 * @author Tim Culpan
 * 
 * NOTE: 	this fileReader uses "hasNext" rather than "hasNextLine"
 * 			Welcome to tweak according to need
 */


public class FileReader {
										
	private String fileName;				// fileName is the file we'll read in
	private ArrayList<String> fileReadIn;	//	and fileReadIn is the ArrayList we'll create
	
	
		
	public FileReader(String file) {			//This is our constructor
		fileName = file;
		fileReadIn = new ArrayList<String>();	//fileReadIn is simply an ArrayList of Strings
		readFile();								//run the readFile method to create the actual object
		
	} //END CONSTRUCTOR 
	
	/** 
	 * This method reads in the file (fileName) and creates an ArrayList of its contents
	 *  to be called fileReadIn
	 **/
	
	private void readFile() {
		try {
			File inputFile = new File(fileName);
			Scanner scannedInput = new Scanner(inputFile);
			
			while (scannedInput.hasNext()) {
				String word = scannedInput.next();
				fileReadIn.add(word);
			}
			in.close();			//close resource
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getfileName() {
		return fileName;
	}

	public ArrayList<String> getfileReadIn() {
		return fileReadIn;
	}
		
}// END FileReader CLASS

