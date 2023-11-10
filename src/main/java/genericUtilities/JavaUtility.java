package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class consists of generic methods related to Java
 * @author Dharmpal
 */
public class JavaUtility {

	public String getSystemDateInFormat()
	/**
	 * This method will return the current system date in specified format
	 * @return currentDate
	 */
	{
		Date d=new Date();
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String currentDate=formatter.format(d);
		return currentDate;
		
	}
	
	/**
	 * This method will generate a random number for every run
	 * @return value
	 */
	public int getRandomNumber()
	{
		Random r=new Random();
		int value=r.nextInt(10000);
		return value;
	}
}
