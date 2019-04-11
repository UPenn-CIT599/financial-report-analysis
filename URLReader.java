import java.util.*;
import java.io.*;
import java.net.*;

public class URLReader {
		private String address; 
		
		//private ArrayList<String> fileReadIn;	//	 fileReadIn is the ArrayList we'll create
		private ArrayList<String> webPageOut;		
			
			//Constructor
		public URLReader(String webPage) {			
			address = webPage;
			webPageOut = new ArrayList<String>();	//fileReadIn is simply an ArrayList of Strings
				readWeb();								//run the readFile method to create the actual object
				
			} //END CONSTRUCTOR 
			
		/**
		 * This method directly accesses the provide URL and reads the contents
		 * creating a ArrayList<String>
		 */
			
			private void readWeb() {
				try {
					URL pageLocation = new URL(address);
					Scanner webPageIn = new Scanner(pageLocation.openStream());
					
					while (webPageIn.hasNext()) {
						String word = webPageIn.next();
						webPageOut.add(word);
					}
					webPageIn.close();			//close resource
				}//END try loop
				catch (Exception e) {
					e.printStackTrace();
				}//END catch loop
			}//END readFile method

			
			//Getters (no setters)
			public String getAddress() {
				return address;
			}

			public ArrayList<String> getWebPageOut() {
				return webPageOut;
			}
				
		}// END FileReader CLASS


