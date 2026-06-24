package practicing.framework.utility;

import java.io.FileReader;
import java.util.Properties;

public class ReadPropFiles {
	
	//Class Variables
	static Properties prop;
	
	public static String ReadConfigFile(String key) throws Exception{
		
		//read in the config file
		FileReader fr = new FileReader ("./testdata/config.properties");
		
		//create new Properties variable and get the data for the specified key
		prop = new Properties();
		prop.load(fr);
		return prop.get(key).toString();
				
	}
	
	public static String ReadElementData(String key) throws Exception{
		
		//read in the config file
		FileReader fr = new FileReader ("./testdata/element.properties");
		
		//create new Properties variable and get the data for the specified key
		prop = new Properties();
		prop.load(fr);
		return prop.get(key).toString();
				
	}
}
