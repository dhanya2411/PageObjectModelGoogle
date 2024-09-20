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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import pages.HomePage;
import util.TestUtil;


public class HomeTest{
  
	  LoginTest lt = new LoginTest();
      TestUtil testUtil;
	  String sheetName = "AddDetails";
	  Logger print = Logger.getLogger("devpinoyLogger");
	
  @BeforeMethod
  public void beforeMethod() throws IOException, InterruptedException  {
	
  lt.tb.OpenBrowser(); //calling browser and url methods from login test
  lt.tb.OpenUrl();
  lt.login(); //calling login method from login test
 
  }

  @Test (priority=1)
  public void HomePageTextVerification() throws InterruptedException {
  HomePage hp = new HomePage(lt.tb.driver);
  
  Thread.sleep(6000);
  Assert.assertEquals(hp.WelcomeText.getText(), "Welcome, Dhanya Iyer", "Text not Matching");
  Assert.assertEquals(hp.manageInforText.getText(), "Manage your info, privacy, and security to make Google work better for you. Learn more", "Text not Matching");
  print.debug("HomeTest - Test 1"); 	  
  } 
  
  @Test(priority=2)
  public void RecoveryDetailsFromPropertyFile() throws InterruptedException {
  HomePage hp = new HomePage(lt.tb.driver);
  
  Thread.sleep(3000);
  hp.AddRecoveryPhone(lt.tb.prop.getProperty("PhoneNumber"));
  Assert.assertEquals(hp.RecoveryInfoText.getText(), "Enter a phone number to use for security purposes. This phone number can be used to help verify it’s really you signing into your account.", "Text not Matching");
  hp.CancelButton.click();
  Thread.sleep(3000);
  hp.BackArrow.click();
  hp.AddRecoveryAddress(lt.tb.prop.getProperty("Address"));
  Assert.assertEquals(hp.RecoveryOptionsText1.getText(), "Your home and work addresses are used to personalize your experiences across Google products, and for more relevant ads. You can remove them any time. Learn more", "Text not matching");
  print.debug("HomeTest - Test 1"); 
  }  
  
  @DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName); //upadated 'TESTDATA_SHEET_PATH' in line 24 -> qa.util package -> TestUtil.java class
		return data;
	}
  
  @Test(priority=3, dataProvider="getTestData") //updated Test Data sheet 
public void RecoveryDetailsFromTestDataSheet(String Address ,String Phone) throws InterruptedException {
	  HomePage hp = new HomePage(lt.tb.driver);
	  
	  Thread.sleep(3000);
	  hp.AddRecoveryAddress(Address);  
	  hp.CancelButton.click();
	  hp.AddRecoveryPhone(Phone);
	  print.debug("HomeTest - Test 1"); 
  }
  
  
  @AfterMethod
  public void afterMethod() {
  lt.tb.CloseBrowser();
  } 



}
