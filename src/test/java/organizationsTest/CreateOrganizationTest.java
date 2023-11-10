package organizationsTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.CreateNewOrgPage;
import objectRepository.HomePage;
import objectRepository.OrgInfoPage;
import objectRepository.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass{

	@Test
	public void createOrganization() throws IOException, InterruptedException {
		
		//Step 1: Read Test Data 
		String ORGNAME=eUtil.readDataFromExcelSheet("Organizations", 1, 2)+jUtil.getRandomNumber();
		
		//Step 2: Navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.clickOnOrgLnk();
		
		//Step 3: click on create organization lookup img
		OrganizationsPage orgp=new OrganizationsPage(driver);
		orgp.clickOnCreateOrgLookUpImg();
		
		//Step 4: Create New Organization with mandatory fields 
		CreateNewOrgPage cNewOrg=new CreateNewOrgPage(driver);
		cNewOrg.createNewOrg(ORGNAME);
		
		//Step 5: Validate
		OrgInfoPage orgInfo=new OrgInfoPage(driver);
		String orgHeader=orgInfo.getOrgHeaderText();
		Assert.assertTrue(orgHeader.contains(ORGNAME));
		System.out.println(orgHeader);
	
	}

}
