package my.framework.support;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadExcel {
	
	//class variables and objects
	private static final Logger logger = TraceManager.getLogger(ReadExcel.class);
	private static XSSFWorkbook excelFile;
	
	@Test
	public void getTableListData() throws IOException{
		logger.info("Entered the getTableListData method.");
		
		//setup the source excel file to read in
		String fileSrc = System.getProperty("user.dir") + "/src/test/resources/DataFiles/ElementTesting.xlsx";
		logger.info("Created the Excel file path: " + fileSrc);
		FileInputStream fis = new FileInputStream(fileSrc);
		List<String[]> mainTableData = new ArrayList<>();
		logger.info("Created the Streaming and Array objects.");
		
		try{
			excelFile = new XSSFWorkbook(fis);
			logger.info("Loaded the Excel workbook");
			
			//get the primary table that will list all the sheet and table names
			mainTableData = readTableData("SheetTableList");
			logger.info("Got the Table SheetTableList and will begin collecting the data from it");
			
			//Read through the table data and get the elements and test tables
			for(int i = 1; i < mainTableData.size(); i++) {
				List<String[]> elementsTableData = new ArrayList<>();
				List<String[]> testTableData = new ArrayList<>();
				logger.info("Created the Arrays to store the names of the different table data types.");

				//String sheetName = mainTableData.get(i)[0];
				String tableName = mainTableData.get(i)[1];
				if (tableName.contains("Element")){
					logger.info("Element Table retrieved: " + tableName);
					elementsTableData = readTableData(tableName);
				} else if (tableName.contains("Test")) {
					logger.info("Test Table retrieved: " + tableName);
					testTableData = readTableData(tableName);
				} else {
					logger.info("Table returned has invalid name: " + tableName);
					logger.info("Skipping to next item in the for loop");
					continue;
				}
				
				//output the data in the array to confirm getting the correct data
				//will replace this with running tests once I have things ready with the classes in the Activities
				if (!elementsTableData.isEmpty()) {
					logger.info("=============================");
					logger.info("Table Name: " + tableName);
					for(int col = 0; col < elementsTableData.size(); col++) {
						for(int row = 0; row < elementsTableData.get(col).length; row++) {
							logger.info(elementsTableData.get(col)[row]);
						}
						logger.info("=============================");
					}
				} else if (!testTableData.isEmpty()) {
					logger.info("=============================");
					logger.info("Table Name: " + tableName);
					for(int col = 0; col < testTableData.size(); col++) {
						for(int row = 0; row < testTableData.get(col).length; row++) {
							logger.info(testTableData.get(col)[row]);
						}
						logger.info("=============================");
					}
				}
			}			
		} catch (Exception e) {
			logger.error("Failed to stream in the Excel file.");
			logger.error("Error Message: " + e.getMessage());
			logger.error("TraceStack: " + e);
		}
		
		
	}
	
	//method to read in the data from a specific table into an array
	private List<String[]> readTableData(String tableName){
		logger.info("Entered the readTableData method.");
		XSSFTable tableList = excelFile.getTable(tableName);
		List<String[]> tableData = new ArrayList<>();
		XSSFSheet sheetObject = excelFile.getTable(tableName).getXSSFSheet();
		logger.info("Created the table and array objects to store the data and created the sheet object to extract the data.");

		//get the table put it into an array and return it
		for(int r = tableList.getStartRowIndex(); r <= tableList.getEndRowIndex(); r++) {
			List<String> colData = new ArrayList<>();
			logger.info("Created the array to collect the columns data.");
			for(int c = tableList.getStartColIndex(); c <= tableList.getEndColIndex(); c++) {
				String data = getCellValue(sheetObject.getRow(r).getCell(c));
				colData.add(data);
				logger.info("Added data: " + data);
				logger.info("From cell: " + new CellReference(r,c).toString());
			}
			tableData.add(colData.toArray(new String[0]));
			logger.info("Added Column array to Table array.");
		}
		logger.info("Returning Table array.");
		return tableData;
	}
	
	//convert everything of the cell into a string 
	private String getCellValue(Cell cell) {
		logger.info("Entered the getCellValue method.");
		if(cell==null || cell.getCellType() == CellType.BLANK ||
				(cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty())) {
			logger.info("Cell was null or empty, returning empty space.");
			return "";
		}
		
		switch (cell.getCellType()) {
			case STRING: {
				logger.info("Cell contained string, returning string value.");
				return cell.getStringCellValue();
			}
			case NUMERIC: {
				if(DateUtil.isCellDateFormatted(cell)) {
					logger.info("Cell contained date, returning date value.");
					return cell.getDateCellValue().toString();
				}
				logger.info("Cell contained number, returning numeric value.");
				return String.valueOf((int)cell.getNumericCellValue());
			}
			case BOOLEAN: {
				logger.info("Cell contained boolean, returning boolean value.");
				return String.valueOf(cell.getBooleanCellValue());
			}
			default: {
				logger.info("Not sure what the cell contained, but its type is " + cell.getCellType() + ", returning empty space.");
				return "";
			}
		}
	}

}
