package PackageofDataProvider;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;


public class DataProviderClass {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	@BeforeTest
	public void setUp()
	{
		report= new ExtentReports("./Reports/Login.html");
		System.setProperty("webdriver.chrome.driver", "d:/Automation/chromedriver.exe");
		driver = new ChromeDriver();
	}
	@Test(dataProvider = "supplyData")
	public void verifyLogin(String username,String password)throws Throwable
	{
		test=report.startTest("Login data");
		driver.get("http://orangehrm.qedgetech.com/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.name("txtUsername")).sendKeys(username);
		driver.findElement(By.name("txtPassword")).sendKeys(password);
		driver.findElement(By.name("Submit")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		String Expected="dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			Reporter.log("Login success::"+username+"   "+password,true);
			test.log(LogStatus.PASS, "Login success::"+username+"    "+password);
		}
		else
		{
			Reporter.log("Login Fail::"+username+"   "+password,true);
			test.log(LogStatus.FAIL, "Login Fail::"+username+"    "+password);
		}
	}

	@DataProvider
	public Object[][] supplyData() 
	{
		Object login [][]= new Object[5][2];//five rows and two columns
		login[0][0]="Admin";
		login[0][1]="Qedge123!@#";
		login[1][0]="test";
		login[1][1]="Qedge123!@#";
		login[2][0]="Admin";
		login[2][1]="Qedge123!@#";
		login[3][0]="Admin";
		login[3][1]="Qedge123!@#";
		login[4][0]="test";
		login[4][1]="Qedge123!@#";
		return login;
	}


	@AfterTest
	public void tearDown()
	{
		report.endTest(test);
		report.flush();
		driver.close();
	}

}
