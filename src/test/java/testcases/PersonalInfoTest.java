/* 
 * 
 * Author - Dhana Lakshmi Krishna
 * 
 */

package testcases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.PersonalInfoPage;
import util.TestUtil;


public class PersonalInfoTest {
	
	
	LoginTest lt = new LoginTest();
	Logger print = Logger.getLogger("devpinoyLogger");
	
	@BeforeMethod
	public void BeforeMethod() throws IOException, InterruptedException {
		lt.tb.OpenBrowser();
		lt.tb.OpenUrl();
		lt.login();
	
	}
	
	@Test (priority=1)
	public void HeaderTextVerification() throws InterruptedException, IOException  {
	PersonalInfoPage pi = new PersonalInfoPage(lt.tb.driver);
	HomePage hp = new HomePage(lt.tb.driver);
	TestUtil util= new TestUtil(lt.tb.driver);
	
	hp.ClickonPersonalInfo();
	Thread.sleep(3000);
		try {
		Assert.assertEquals(pi.PersonalInfoHeader.getText(), "Personal info", "Text not matching");		
		Assert.assertEquals(pi.PersonalInfoHeaderText.getText(), "Info about you and your preferences across Google services", "Text not matching");
		Assert.assertEquals(pi.PersonalInfoHeader2.getText(), "Your profile info in Google services", "Text not matching");
		Assert.assertEquals(pi.PersonalInfoHeaderText2.getText(), "Personal info and options to manage it. You can make some of this info, like your contact details, visible to others so they can reach you easily. You can also see a summary of your profiles.", "Text not matching");
		Assert.assertEquals(pi.PersonalInfoHeader3.getText(), "Basic info", "Text not matching");
		Assert.assertEquals(pi.PersonalInfoHeaderText3.getText(), "Some info may be visible to other people using Google services. Learn more", "Text not matching");
		}catch(Exception e) {
	    	util.takeScreenshotAtEndOfTest();
	    }
		print.debug("PersonalInfoTest - Test 1"); 
		
	}
	
	@Test (priority=2)
	public void BasicInfo() throws InterruptedException {
		PersonalInfoPage pi = new PersonalInfoPage(lt.tb.driver);
		HomePage hp = new HomePage(lt.tb.driver);
		
		hp.ClickonPersonalInfo();
		pi.BasicInfo();
		print.debug("PersonalInfoTest - Test 2");
		
	} 
	
	@Test (priority=3)
	public void ContactInfo() throws InterruptedException {
		PersonalInfoPage pi = new PersonalInfoPage(lt.tb.driver);
		HomePage hp = new HomePage(lt.tb.driver);
		
		hp.ClickonPersonalInfo();
		pi.ContactInfo();		
		Assert.assertEquals(pi.ContactInfo.getText(), "Contact info", "Text not matching");
		print.debug("PersonalInfoTest - Test 3");
		
	} 
	
	@AfterMethod
	public void AfterMethod() {
		lt.tb.CloseBrowser();	
	} 
	

}
