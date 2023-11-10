package practice;

import java.io.FileOutputStream;
import java.util.Properties;

public class WriteDataInPropertyFile {

	public static void main(String[] args) throws Throwable{
		Properties p=new Properties();
		p.setProperty("name", "dharmpal");
		FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\CommonData.properties");
		p.store(fos, "New File Created");
		System.out.println("Data created in property file");
	}

}
