package com.qa.raybiztech.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
*
This class provides utility methods to read data from an Excel file.
It uses Apache POI library to read data from the Excel file.
*
*/
public class ExcelUtils {
	
	private static String TEST_DATA_SHEET = "./src/test/resources/testdata/Rbt_Automation_Testdata.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	
	/**
	 * This method reads test data from the specified sheet of an Excel file.
	 * @param sheetName - Name of the sheet containing test data
	 * @return - 2D array of test data
	 */
	public static Object[][] getTestData(String sheetName) {
		
		Object data[][] = null;
		
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; 
			for(int i = 0; i <sheet.getLastRowNum(); i++) {
				for (int j = 0; j <sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
