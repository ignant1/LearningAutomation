package filehandling;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandling {

	public static void ReadExcel() throws Exception {
		FileInputStream fls = new FileInputStream("./TestData/ExcelData.xlsx");
		
		try (XSSFWorkbook wb = new XSSFWorkbook(fls)) {
			XSSFSheet sht = wb.getSheet("Sheet1");
			
			XSSFRow row1 = sht.getRow(0);			
			XSSFCell cellA1 = row1.getCell(0);
			XSSFCell cellB1 = row1.getCell(1);
			XSSFRow row2 = sht.getRow(1);			
			XSSFCell cellA2 = row2.getCell(0);
			XSSFCell cellB2 = row2.getCell(1);
			
			System.out.println("Cell A1 contains: " + cellA1.getStringCellValue());
			System.out.println("Cell B1 contains: " + cellB1.getNumericCellValue());
			System.out.println("Cell A2 contains: " + cellA2.getBooleanCellValue());
			System.out.println("Cell B2 contains: " + cellB2.getDateCellValue());
		}
	}
	
	public static void main(String[] args) throws Exception{
		ReadExcel();
	}
}
