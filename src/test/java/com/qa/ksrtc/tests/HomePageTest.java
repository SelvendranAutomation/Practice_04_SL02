package com.qa.ksrtc.tests;



import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.ksrtc.pages.HomePage;
import com.qa.ksrtc.pages.TestBase;
import com.qa.ksrtc.pages.TestUtil;
import com.qa.ksrtc.pages.LoginPage;



public class HomePageTest {
	
	public WebDriver driver;
	public TestBase testBase;
	public Properties prop;
	public HomePage homePage;
	public LoginPage loginPage;

	
	@BeforeTest
	public void setUp(){
		testBase = new TestBase();	
		prop = testBase.initProperty();
		driver = testBase.init(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));
		homePage =  new HomePage(driver);
		loginPage = new LoginPage(driver);
		
	}
	@DataProvider
	public Object[][] getData(){
		Object testData[][] = TestUtil.getDataFromSheet(TestUtil.dataSheetName);
		return testData;
	}


	
	@Test(priority = 0)
	public void verifyManinPageOfKSRTC(){


		
		String title = homePage.getHomePageTitle();
		System.out.println("Home page title is: "+ title);
		Assert.assertEquals(title, "KSRTC Official Website for Online Bus Ticket Booking - KSRTC.in");
				
		String  landingPagePhoneNoText = homePage.getLandingPagePhoneNoText();	

		System.out.println("\"Home page LandingPagePhoneNo : "+ landingPagePhoneNoText);
		Assert.assertEquals(landingPagePhoneNoText, "080-26252625");


	}
	@Test(priority = 1)
	public void verifySignIn(){


		
		homePage.clickLlandingPageSignIn_link();
		String loginPageTitle = loginPage.getLoginPageTitle();
		System.out.println("Login page title is: "+ loginPageTitle);
		
				
		String loginPageURL = loginPage.getLoginPageURL();

		System.out.println("\"Login pagee URL : "+ loginPageURL);
		Assert.assertEquals(loginPageURL, "https://ksrtc.in/oprs-web/login/show.do");
		
		boolean[] loginPageList = loginPage.verifyLoginForm();

		Assert.assertTrue(loginPageList[0]);
		Assert.assertTrue(loginPageList[1]);
		Assert.assertTrue(loginPageList[2]);
	}
	
	@Test(dataProvider="getData", priority = 2)
	public void verifyLogin(String UserName,	String PassWord, String EmailID){
		homePage = loginPage.login(UserName,UserName);
		
		String errorMessageText = loginPage.getErrorMessageText();
		Assert.assertEquals(errorMessageText, "Login incorrect. Please try again");
		
		
	}
	
	
	@Test(dataProvider="getData", priority = 3)
	public void verifyForgotPassword(String UserName,	String PassWord, String EmailID){
		homePage.clickLlandingPageSignIn_link();
		loginPage.clickForgotPassword_link();
		loginPage.enterForForgotPassword(EmailID);
		String errorMessageText = loginPage.getErrorMessageText();
		Assert.assertEquals(errorMessageText, "Login Name not found in the system.");
		
		
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
	
	
	

}
