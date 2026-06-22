package filehandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class FileHandling {
	static File f = new File("./TestData/FirstFileRead.txt");
	
	public static void ReadByCharacter() throws Exception {		
		FileReader fr = new FileReader(f);
		
		System.out.println(fr.read()); //read will only get the first character ascii number.
		
		System.out.println((char)fr.read()); //Casting to char will display the character that was read, but this will read the second character in the line.
		
		try (FileReader fr2 = new FileReader(f)) {
			int c = fr2.read();
			
			// Read will return a -1 when there are no more characters to read. 
			while(c != -1) {
				System.out.print((char)c);
				c = fr2.read();
			}
		}
		System.out.println("");
		
		fr.close();
	}
	
	
	  public static void ReadByLine() throws Exception {
		  
		  BufferedReader br = new BufferedReader(new FileReader(f));
		  
		  String line = br.readLine();
		  System.out.println("Now reading the entire line.");
		  while(line != null) {
			  System.out.println(line);
			  line = br.readLine();
		  }
		  
		  br.close();
	  }
	 
	
	public static void main(String[] args) throws Exception {
		ReadByCharacter();
		ReadByLine()
;	}

}