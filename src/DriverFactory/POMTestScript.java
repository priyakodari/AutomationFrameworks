package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageLayer.AddEmpPage;
import PageLayer.AddUserPage;
import PageLayer.LoginPage;
import PageLayer.LogoutPage;


public class POMTestScript {
	WebDriver driver;
	@BeforeMethod
	public void setUp()throws Throwable
	{
		System.setProperty("webdriver.chrome.driver", "./CommonDrivers/chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("http://orangehrm.qedgetech.com/");
		driver.manage().window().maximize();
		LoginPage login =PageFactory.initElements(driver, LoginPage.class);
		login.verifyLogin("Admin", "Qedge123!@#");
	}
	@Test
	public void userAdd()throws Throwable
	{
		AddUserPage user =PageFactory.initElements(driver, AddUserPage.class);
		user.verifyUser("Adity Sri215", "Akhilesh700", "Akhilesh@12345", "Akhilesh@12345");
	}
	@Test
	public void empAdd()throws Throwable
	{
		AddEmpPage emp = PageFactory.initElements(driver, AddEmpPage.class);
		emp.verifyEmp("Selenium", "Testing", "QTP");
	}
	@AfterMethod
	public void tearDown()throws Throwable
	{
		LogoutPage logout = PageFactory.initElements(driver, LogoutPage.class);
		logout.verifyLogout();
		driver.close();
	}
	}

	
	


