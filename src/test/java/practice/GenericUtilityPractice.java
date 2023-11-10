package practice;

import java.io.IOException;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;

public class GenericUtilityPractice {

	public static void main(String[] args) throws IOException {
		
		//Scripts
		//Common Data
		PropertyFileUtility pUtil=new PropertyFileUtility();
		String value = pUtil.readDataFromPropertyFile("browser");
		System.out.println(value);
		String value1 = pUtil.readDataFromPropertyFile("url");
		System.out.println(value1);
		String value2 = pUtil.readDataFromPropertyFile("username");
		System.out.println(value2);
		String value3 = pUtil.readDataFromPropertyFile("password");
		System.out.println(value3);
		
		//Date
		JavaUtility jUtil=new JavaUtility();
		String date=jUtil.getSystemDateInFormat();
		System.out.println(date);
		
		//Random Number
		int ran=jUtil.getRandomNumber();
		System.out.println(ran);
		
		//Test Data
		ExcelFileUtility excelUtil=new ExcelFileUtility();
		String ORGNAME=excelUtil.readDataFromExcelSheet("Organizations", 4, 2);
		String ORGNAMEWithRandom=ORGNAME+ran;
		System.out.println(ORGNAME);
		System.out.println(ORGNAMEWithRandom);
		
		String LASTNAME=excelUtil.readDataFromExcelSheet("Contacts", 4, 2);
		String LASTNAMEWithRandom=LASTNAME+ran;
		System.out.println(LASTNAME);
		System.out.println(LASTNAMEWithRandom);

	}

}
