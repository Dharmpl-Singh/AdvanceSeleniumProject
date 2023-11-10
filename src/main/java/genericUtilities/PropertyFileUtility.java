package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class consist of reusable methods related to property file
 * @author Dharmpal
 */
public class PropertyFileUtility {
	
	/**
	 * This method will read the data from property file for the key provided by called
	 * and return the value to called
	 * @param key
	 * @return value
	 * @throws IOException
	 */
	
	public String readDataFromPropertyFile(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p=new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;
	}

}
