package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class consist of reusable methods related to excel sheet
 * @author Dharmpal
 */
public class ExcelFileUtility {
	/**
	 * This method is used to read the data from Excel Sheet for the row and cell provide by caller
	 * and it will return value to the caller
	 * @param row
	 * @param cell
	 * @param key
	 * @return value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	public String readDataFromExcelSheet(String sheetName, int rowNo,int cellNo) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData2.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String value=wb.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();
		return value;
	}
	
	/**
	 * This method will read multiple data from excel and help to provide data to dataProvider
	 * @param sheetName
	 * @return data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public Object[][] readMultipleDataFromExcelSheet(String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\DataProvider.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		 Sheet sh = wb.getSheet(sheetName);
		 int lastRow = sh.getLastRowNum();
		 int lastCell = sh.getRow(0).getLastCellNum();
		 
		 Object[][] data=new Object[lastRow][lastCell];
		 for(int i=0;i<lastRow;i++)
		 {
			 for(int j=0;j<lastCell;j++)
			 {
				 data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			 }
		 }
		return data;
	}
	
	public Object[][] readMultipleContactDataFromExcelSheet(String sheetname) throws Throwable
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\DataProvider.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetname);
		int lastRow=sh.getLastRowNum();
		int lastCell=sh.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[lastRow][lastCell];
		
		for(int i=0;i<lastRow;i++)
		{
			for(int j=0;j<lastCell;j++)
			{
				data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		
		return data;
	}

}
