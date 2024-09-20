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

import base.TestBase;
import pages.LoginPage;



public class LoginTest {
	
	
	TestBase tb = new TestBase();
	Logger print = Logger.getLogger("devpinoyLogger");
	

	
  @BeforeMethod
   public void beforeMethod() throws IOException {
	  tb.OpenBrowser();
	  tb.OpenUrl();  
	  
  }

  @Test (priority=1)
  public void login() throws InterruptedException, IOException {
	  LoginPage lp = new LoginPage(tb.driver);
	
	  Thread.sleep(3000);
	  lp.LoginEmail(tb.prop.getProperty("email"));	 
	  lp.ClickNextButton();
	  Thread.sleep(3000);
	  lp.LoginPassword(tb.prop.getProperty("password"));
	  Thread.sleep(3000);
	  lp.ClickNextButton();
	  Assert.assertEquals(lp.Title(), "Sign in - Google Accounts");
	  print.debug("LoginTest - Test 1"); 
	  
  }
  
  @Test(priority=2)
  public void ForgotEmail() throws InterruptedException, IOException {
	  LoginPage lp = new LoginPage(tb.driver);
	  
	  lp.ForgotEmailLink();
	  lp.RecoveryEmail(tb.prop.getProperty("RecoveryEmail"));
	  Thread.sleep(3000);
	  lp.ClickNextButton();
	  Thread.sleep(3000);
	  lp.RecoveryEmailDetails(tb.prop.getProperty("FirstName"), tb.prop.getProperty("LastName"));
	  lp.ClickNextButton();
	  Assert.assertEquals(lp.Title(), "Sign in - Google Accounts");
	  print.debug("LoginTest - Test 2"); 
  }
 
  @Test(priority=3)
  public void LearnMoreAboutUSingGuestMode() throws InterruptedException, IOException {
	  LoginPage lp = new LoginPage(tb.driver);
	  
	  Assert.assertEquals(lp.LearnMoreLinkTextVerification(), "Not your computer? Use a Private Window to sign in. Learn more about using Guest mode");
	  lp.LearnMoreLink();
	  Thread.sleep(3000);
	  Assert.assertEquals(lp.Title(), "Sign in on a device that's not yours - Computer - Google Account Help");
	  print.debug("LoginTest - Test 3"); 
  }
  
  
  
  @AfterMethod
  public void afterMethod() {
	  tb.CloseBrowser();

  } 

}
