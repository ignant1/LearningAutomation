package support.beamsss;

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
		List<String[]> tableData = new ArrayList<>();
		try{
			excelFile = new XSSFWorkbook(fis);
			
			//get the primary table that will list all the sheet and table names
			XSSFTable tablelist = excelFile.getTable("SheetTableList");
			tableData = readTableData(tablelist.getStartRowIndex(), tablelist.getEndRowIndex(),
					tablelist.getStartColIndex(), tablelist.getEndColIndex(), tablelist.getSheetName());
			
			//for()
			System.out.println("tableData.size() =  " + tableData.size());
			System.out.println("tableData.get(0).length =  " + tableData.get(0).length);
			System.out.println("tableData.get(0)[0] =  " + tableData.get(0)[0]);
			System.out.println("tableData.get(1)[0] =  " + tableData.get(1)[0]);
			System.out.println("tableData.get(1)[0] =  " + tableData.get(2)[0]);
			System.out.println("tableData.get(1)[0] =  " + tableData.get(3)[0]);
			System.out.println("tableData.get(0)[1] =  " + tableData.get(0)[1]);
			System.out.println("tableData.get(1)[1] =  " + tableData.get(1)[1]);
			System.out.println("tableData.get(0)[1] =  " + tableData.get(2)[1]);
			System.out.println("tableData.get(1)[1] =  " + tableData.get(3)[1]);
			//for(int r = 0; r < rowEnd; r++) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	//method to read in the data from a specific table into an array
	public List<String[]> readTableData(int rowStart, int rowEnd, int colStart, int colEnd, String tableName){
		List<String[]> tableData = new ArrayList<>();

		//get the table put it into an array and return it
		XSSFSheet sheetObject = excelFile.getTable(tableName).getXSSFSheet();
		for(int r = rowStart; r <= rowEnd; r++) {
			List<String> rowData = new ArrayList<>();
			for(int c = colStart; c <= colEnd; c++) {
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
