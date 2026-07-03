package demo.project.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import demo.project.base.BaseClass;

public class ExcelReaderManager {
	
	public static final Logger logger = BaseClass.logger;
	
	public static List<String[]> getSheetData(String filePath, String sheetName){
		logger.info("Running ExcelReaderManager.getSheetData");
		logger.info("Excel path received is: " + filePath);
		//list data variable to gather column data from excel
		List<String[]> data = new ArrayList<>();
		logger.info("Initialized the array");
		//create object to read the steam of data
		try( 
			//Load the excel file
			FileInputStream fis = new FileInputStream(filePath);
			Workbook excelFile = new XSSFWorkbook(fis)){
			logger.info("Made it into the try/catch since we got the Excel file.");
			//Get the data from the excel sheet
			
			Sheet sheet = excelFile.getSheet(sheetName);
			if(sheet==null) {
				logger.info("Sheet " + sheetName + " does not exist.");
				throw new IllegalArgumentException("Sheet " + sheetName + " does not exist.");
			}
			
			//Iterate through the rows
			logger.info("going through the rows to get the data.");
			for(Row row:sheet) {
				if(row.getRowNum()==0) {
					continue;
				}
				
				//read all the cells in the row
				List<String> rowData = new ArrayList<>();
				for(Cell cell:row) {
					rowData.add(getCellValue(cell));
				}
				
				//convert rowData to String[]
				data.add(rowData.toArray(new String[0]));
			}
			
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return data;
	}
	
	private static String getCellValue(Cell cell) {
		logger.info("Running ExcelReaderManager.getCellValue");
		if(cell==null) {
			return "";
		}
		
		switch (cell.getCellType()) {
			case STRING: {
				return cell.getStringCellValue();
			}
			case NUMERIC: {
				if(DateUtil.isCellDateFormatted(cell)) {
					return cell.getDateCellValue().toString();
				}
				return String.valueOf((int)cell.getNumericCellValue());
			}
			case BOOLEAN: {
				return String.valueOf(cell.getBooleanCellValue());
			}
			default: {
				return "";
			}
		}
	}
}
