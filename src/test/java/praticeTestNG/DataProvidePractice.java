package praticeTestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvidePractice {
	
	@Test(dataProvider="getData")
	public void readData(String name,String model,int qty,int price)
	{
		System.out.println(name+"----"+model+"----"+qty+"----"+price);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data=new Object[3][4];
		
		data[0][0]="Samsung";
		data[0][1]="A20";
		data[0][2]=10;
		data[0][3]=12000;
		
		data[1][0]="Iphone";
		data[1][1]="14Pro";
		data[1][2]=20;
		data[1][3]=75000;
		
		data[2][0]="Redmi";
		data[2][1]="12Pro";
		data[2][2]=30;
		data[2][3]=15000;
		
		return data;
	}

}
