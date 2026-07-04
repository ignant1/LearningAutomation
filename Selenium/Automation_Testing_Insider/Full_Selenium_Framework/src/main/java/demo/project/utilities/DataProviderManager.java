package demo.project.utilities;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import demo.project.base.BaseClass;

public class DataProviderManager {
	
	private static final String FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/TestData/TestData.xlsx";
	public static final Logger logger = BaseClass.logger;
	
	@DataProvider(name="ValidLoginData")
	public static Object[][] ValidLoginData(){
		logger.info("Running DataProviderManager.ValidLoginData");
		return getSheetData("ValidLoginData");
	}

	@DataProvider(name="OrnageHRMLogo")
	public static Object[][] OrnageHRMLogo(){
		logger.info("Running DataProviderManager.OrnageHRMLogo");
		return getSheetData("ValidLoginData");
	}

	@DataProvider(name="InvalidLoginData")
	public static Object[][] InvalidLoginData(){
		logger.info("Running DataProviderManager.InvalidLoginData");
		return getSheetData("InvalidLoginData");
	}

	private static Object[][] getSheetData(String sheetName){
		logger.info("Running DataProviderManager.getSheetData");
		List<String []> sheetData = ExcelReaderManager.getSheetData(FILE_PATH, sheetName);
		Object[][] data = new Object[sheetData.size()][sheetData.get(0).length];
		
		//copy the excel sheet data from the list to the array
		for(int i=0; i<sheetData.size(); i++) {
			data[i] = sheetData.get(i);
		}
		
		return data;
	}
}
