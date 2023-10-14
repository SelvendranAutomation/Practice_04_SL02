package com.qa.ksrtc.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;



public class LoginPage extends TestBase {

	private By userName_id = By.id( "userName");
	private By password_id = By.id( "password");
	private By submitBtn_id = By.id( "submitBtn");
	private By errorMsg_id = By.id( "errorMsg");
	private By termsConditions_id = By.id( "TermsConditions");
	private By forgotPassword_link = By.linkText( "Forgot Password");
	 
	
	
	
	private ElementUtil elementUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		 
	}

	public HomePage login(String userName, String pwd) {
		elementUtil.waitForElementPresent(userName_id, 30);
		driver.findElement(userName_id).clear();
		driver.findElement(userName_id).sendKeys(userName);
		driver.findElement(password_id).clear();
		driver.findElement(password_id).sendKeys(pwd);
		//driver.findElement(password_id).click();
		performKeyEvents();

//		JavascriptExecutor js = ((JavascriptExecutor) driver);
//		js.executeScript("arguments[0].click();", termsConditions_id);
		driver.findElement(submitBtn_id).click();
		
		return new HomePage(driver);

	}

	public String getLoginPageTitle() {
		elementUtil.waitForElementPresent(userName_id, 30);
		return driver.getTitle();
	}
	public String getLoginPageURL() {
		elementUtil.waitForElementPresent(userName_id, 30);
		return driver.getCurrentUrl();
	}

	public boolean[] verifyLoginForm() {
		elementUtil.waitForElementPresent(userName_id, 30);
		return new boolean[] {driver.findElement(userName_id).isDisplayed(), driver.findElement(password_id).isDisplayed(),driver.findElement(submitBtn_id).isDisplayed()};

	}
	
	
	public String getErrorMessageText() {
		elementUtil.waitForElementPresent(errorMsg_id, 30);
		elementUtil.getScreenshot();	
		return driver.findElement(errorMsg_id).getText();
	}
	
    public void performKeyEvents () {

        Robot robot = null;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        robot.delay(20);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);

        robot.keyPress(KeyEvent.VK_SPACE);
        robot.delay(150);
        robot.keyRelease(KeyEvent.VK_SPACE);
    }
    
	public void clickForgotPassword_link() {
		elementUtil.waitForElementPresent(userName_id, 30);
		driver.findElement(forgotPassword_link).click();
	}
	
	
	public void enterForForgotPassword(String userName) {
		elementUtil.waitForElementPresent(userName_id, 30);
		driver.findElement(userName_id).clear();
		driver.findElement(userName_id).sendKeys(userName);
		driver.findElement(submitBtn_id).click();
		
	}
	
	
	
	
}
