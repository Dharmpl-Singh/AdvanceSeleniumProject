package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataInExcelFile {

	public static void main(String[] args) throws Throwable {
		   
			    //Step 1: Open the document in Java Readable format
				FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
				
				//Step 2: Create a workbook
				Workbook wb = WorkbookFactory.create(fis);	
				
				Sheet sh=wb.getSheet("Organizations");
				Row rw=sh.createRow(16);
				Cell cl=rw.createCell(0);
				cl.setCellValue("Dharmpal");
				FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
				wb.write(fos);
				System.out.println("Data Inserted in Excel Sheet");
				

	}

}
