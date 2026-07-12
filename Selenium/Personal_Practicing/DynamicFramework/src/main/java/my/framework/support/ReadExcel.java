package my.framework.support;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadExcel {
	private static XSSFWorkbook excelFile;
	
	@Test
	public void getTableListData() throws IOException{
		//setup the source excel file to read in
		String fileSrc = System.getProperty("user.dir") + "/src/test/resources/DataFiles/ElementTesting.xlsx";
		FileInputStream fis = new FileInputStream(fileSrc);
		List<String[]> mainTableData = new ArrayList<>();
		
		try{
			excelFile = new XSSFWorkbook(fis);
			
			//get the primary table that will list all the sheet and table names
			mainTableData = readTableData("SheetTableList");
			
			//Read through the table data and get the elements and test tables
			for(int i = 1; i < mainTableData.size(); i++) {
				List<String[]> elementsTableData = new ArrayList<>();
				List<String[]> testTableData = new ArrayList<>();

				//String sheetName = mainTableData.get(i)[0];
				String tableName = mainTableData.get(i)[1];
				if (tableName.contains("Element")){
					elementsTableData = readTableData(tableName);
				} else if (tableName.contains("Test")) {
					testTableData = readTableData(tableName);
				} else {
					System.out.println("Table returned has invalid name.");
					continue;
				}
				
				//output the data in the array to confirm getting the correct data
				if (!elementsTableData.isEmpty()) {
					System.out.println("=============================");
					System.out.println("Table Name: " + tableName);
					for(int col = 0; col < elementsTableData.size(); col++) {
						for(int row = 0; row < elementsTableData.get(col).length; row++) {
							System.out.println(elementsTableData.get(col)[row]);
						}
						System.out.println("=============================");
					}
				} else if (!testTableData.isEmpty()) {
					System.out.println("=============================");
					System.out.println("Table Name: " + tableName);
					for(int col = 0; col < testTableData.size(); col++) {
						for(int row = 0; row < testTableData.get(col).length; row++) {
							System.out.println(testTableData.get(col)[row]);
						}
						System.out.println("=============================");
					}
				}
			}
			
			
			/*System.out.println("tableData.size() =  " + mainTableData.size());
			System.out.println("tableData.get(0).length =  " + mainTableData.get(0).length);
			System.out.println("tableData.get(0)[0] =  " + mainTableData.get(0)[0]);
			System.out.println("tableData.get(1)[0] =  " + mainTableData.get(1)[0]);
			System.out.println("tableData.get(1)[0] =  " + mainTableData.get(2)[0]);
			System.out.println("tableData.get(1)[0] =  " + tableData.get(3)[0]);
			System.out.println("tableData.get(0)[1] =  " + tableData.get(0)[1]);
			System.out.println("tableData.get(1)[1] =  " + tableData.get(1)[1]);
			System.out.println("tableData.get(0)[1] =  " + tableData.get(2)[1]);
			System.out.println("tableData.get(1)[1] =  " + tableData.get(3)[1]);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	//method to read in the data from a specific table into an array
	private List<String[]> readTableData(String tableName){
		XSSFTable tableList = excelFile.getTable(tableName);
		List<String[]> tableData = new ArrayList<>();

		//get the table put it into an array and return it
		XSSFSheet sheetObject = excelFile.getTable(tableName).getXSSFSheet();
		for(int r = tableList.getStartRowIndex(); r <= tableList.getEndRowIndex(); r++) {
			List<String> rowData = new ArrayList<>();
			for(int c = tableList.getStartColIndex(); c <= tableList.getEndColIndex(); c++) {
				rowData.add(getCellValue(sheetObject.getRow(r).getCell(c)));
			}
			tableData.add(rowData.toArray(new String[0]));
		}
		
		return tableData;
	}
	
	//convert everything of the cell into a string 
	private String getCellValue(Cell cell) {
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
