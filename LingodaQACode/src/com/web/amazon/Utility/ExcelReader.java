/*Excel Reader Package
 * This package is created to read the test data from excel file
*/
package com.web.amazon.Utility;

//Import required packages
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Class 'ExcelReader'
public class ExcelReader {
    
	//Variable declaration
	private static ExcelReader reader;
	private File file;
	
	//getSheet method is used to access the excel sheetname
	private XSSFSheet getSheet(String sheetName)
	{
		XSSFWorkbook workbook;
		XSSFSheet sheet = null;
		try
		{
			workbook = new XSSFWorkbook(new FileInputStream(file));
			sheet = workbook.getSheet(sheetName);
			//logger.info(" Data will be read from the sheet :" + sheetName);
			return sheet;
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return sheet;
	}
	
	//getColumns method is used to fetch the column names
	private List<String> getColumns(XSSFSheet sheet)
	{
		final XSSFRow row = sheet.getRow(0);
		final List<String> columnValues = new ArrayList<String>();
		final int firstCellNum = row.getFirstCellNum();
		final int lastCellNum = row.getLastCellNum();
		for (int i = firstCellNum; i < lastCellNum; i++)
		{
			final XSSFCell cell = row.getCell(i);
			columnValues.add(cell.getStringCellValue());
		}
		return columnValues;
	}
	
	//getSheetData method is used to fetch the column values
	private Map<String, String> getSheetData(String tcID, String sheetName)
	{
		final List<String> rowData = new ArrayList<String>();
		final Map<String, String> rowVal = new HashMap<String, String>();
		Object value = null;
		final XSSFSheet sheet = getSheet(sheetName);
		final List<String> coulmnNames = getColumns(sheet);
		final int totalRows = sheet.getPhysicalNumberOfRows();
		final XSSFRow row = sheet.getRow(0);
		final int firstCellNum = row.getFirstCellNum();
		final int lastCellNum = row.getLastCellNum();
		for (int i = 1; i < totalRows; i++)
		{
			final XSSFRow rows = sheet.getRow(i);
			System.out.println(rows.getCell(0).getStringCellValue());
			final String testLinkID = rows.getCell(0).getStringCellValue();
			if (tcID.equalsIgnoreCase(testLinkID))
			{
				for (int j = firstCellNum; j < lastCellNum; j++)
				{
					final XSSFCell cell = rows.getCell(j);
					if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK)
					{
						rowData.add("");
					}
					else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					{
						final Double val = cell.getNumericCellValue();
						value = val.intValue();// cell.getNumericCellValue();
						rowData.add(value.toString());
					}
					else if (cell.getCellType() == Cell.CELL_TYPE_STRING)
					{
						rowData.add(cell.getStringCellValue());
					}
					else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN || cell.getCellType() == Cell.CELL_TYPE_ERROR
									|| cell.getCellType() == Cell.CELL_TYPE_FORMULA)
					{
						throw new RuntimeException(" Cell Type is not supported ");
					}
					rowVal.put(coulmnNames.get(j), rowData.get(j).trim());

				}
				break;
			}

		}
		return rowVal;

	}

	public Map<String, String> getRowValue(String tcID, String sheetName)
	{
		final Map<String, String> rowData = getSheetData(tcID, sheetName);
		if (!rowData.isEmpty())
		{
			return rowData;
		}
		else
		{
			throw new NullPointerException(tcID + " : doen't exist in " + sheetName + " sheet");
		}
	}
	

	/**
	 * This method reads file path in constructor.
	 * Parameters: filePath
	 */
	private ExcelReader(String filePath) throws FileNotFoundException
	{
		file = new File(filePath);
	}
	
	/**
	 * This method creates instance for ExcelReader and returns the ExcelReaderInstance.
	 * Parameter: filePath - Path of the file
	 */
	public static ExcelReader getInstance(String filePath)
	{
		try
		{
			reader = new ExcelReader(filePath);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return reader;
	}
}
