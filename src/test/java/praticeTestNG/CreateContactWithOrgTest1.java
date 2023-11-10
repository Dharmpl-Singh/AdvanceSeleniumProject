package praticeTestNG;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrgPage;
import objectRepository.HomePage;
import objectRepository.OrgInfoPage;
import objectRepository.OrganizationsPage;

public class CreateContactWithOrgTest1 extends BaseClass{

	@Test(groups = "RegressionSuite")
	public void createContWithOrgTest() throws IOException, InterruptedException
	{

		
		
		String ORGNAME=eUtil.readDataFromExcelSheet("Organizations", 1, 2)+jUtil.getRandomNumber();
		String LASTNAME=eUtil.readDataFromExcelSheet("Contacts", 1, 2)+jUtil.getRandomNumber();
				
		//Step 4: Navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.clickOnOrgLnk();
		
		//Step 5: click on create organization lookup img
		OrganizationsPage orgp=new OrganizationsPage(driver);
		orgp.clickOnCreateOrgLookUpImg();
		
		//Step 6: Create New Organization with mandatory fields 
		CreateNewOrgPage cNewOrg=new CreateNewOrgPage(driver);
		cNewOrg.createNewOrg(ORGNAME);
		
		//Step 7: Validate
		OrgInfoPage orgInfo=new OrgInfoPage(driver);
		String orgHeader=orgInfo.getOrgHeaderText();
		Assert.assertTrue(orgHeader.contains(ORGNAME));
		System.out.println(orgHeader+"Organization Created");
		
		//Step 8: Navigate to contacts module
		hp.clickOnContactLnk();
		
		//Step 9: click on create contact lookup img
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactLookUpIcon();
		
		//Step 10: create contact with orgname
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContact(driver, ORGNAME, LASTNAME);
		
		//Step 11: Validate
		ContactInfoPage ciPage=new ContactInfoPage(driver);
		String contactHeader = ciPage.getContactHeaderText();		
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println("PASS");
		
	}

}
