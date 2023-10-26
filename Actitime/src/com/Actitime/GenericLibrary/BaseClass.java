package com.Actitime.GenericLibrary;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Actitime.pom.HomePage;
import com.Actitime.pom.LoginPage;

public class BaseClass {
	
	public  static WebDriver driver;
	FileLibrary f=new FileLibrary();
	
	@BeforeSuite
	public void dataaseConnection()
	{
		Reporter.log("Database connected",true);
	}
	
	@BeforeClass
	public void launchBrowser() throws IOException
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String URL = f.readDataFromProperty("url");
		driver.get(URL);
		
	}
	
	@BeforeMethod
	public void login() throws IOException {
		LoginPage lp = new LoginPage(driver);
		String un = f.readDataFromProperty("un");
		String pw = f.readDataFromProperty("pw");
		lp.getUntbx().sendKeys(un);
		lp.getPwtbx().sendKeys(pw);
		lp.getLgbtn().click();
		
		Reporter.log("Logged in Successfully",true);
		
	}
	
	@AfterMethod
	public void Logout() {
		HomePage hp= new HomePage(driver);
		hp.getLgoutlnk().click();
		
		Reporter.log("Logged out Successfully",true);
		
	}

		@AfterClass
		public void CloseBrowser() {
			driver.close();
			Reporter.log("Browser closed Successfully",true);
			
		}
		
		@AfterSuite
		public void databasedisc() {
			Reporter.log("Database disconnected",true);
		}
}



