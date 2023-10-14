package com.qa.ksrtc.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class HomePage extends TestBase{
	
	private ElementUtil elementUtil;
	
	private By landingPagePhoneNo_css = By.cssSelector( "ul[class='col-md-3 fl-left']>li>a");
	private By landingPageSignIn_link = By.linkText( "Sign In");

	
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	

	public String getLandingPagePhoneNoText() {
		elementUtil.waitForElementPresent(landingPagePhoneNo_css, 30);
		return driver.findElement(landingPagePhoneNo_css).getText();
	}
	
	public void clickLlandingPageSignIn_link() {

		driver.findElement(landingPageSignIn_link).click();
	}
	
	public String getHomePageTitle(){
		return driver.getTitle();
	}
	
}
