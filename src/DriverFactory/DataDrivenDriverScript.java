package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExcelFileUtil;

public class DataDrivenDriverScript {
WebDriver driver;
ExtentReports report;
ExtentTest test;
File screen;
String inputpath="D:\\Automation\\Automation_Frameworks\\TestInput\\LoginTest.xlsx";
String outputpath="D:\\Automation\\Automation_Frameworks\\TestOutput\\LoginResults.xlsx";
@BeforeTest
public void setUp()
{
	report= new ExtentReports("./ExtentReport/Login.html");
	System.setProperty("webdriver.chrome.driver", "./CommonDrivers/chromedriver.exe");
	driver = new ChromeDriver();
}
@Test
public void verifyLogin()throws Throwable
{
	//access excel methods
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of row in a login sheet
	int rc =xl.rowCount("Login");
	//count no of cells in a login sheet
	int cc =xl.cellCount("Login");
	Reporter.log("No of rows::"+rc+"  "+"No of cells ::"+cc,true);
	for(int i=1; i<=rc; i++)
	{
		driver.get("http://orangehrm.qedgetech.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		test=report.startTest("Validate Login");
		//read cell data
		String username =xl.getCellData("Login", i, 0);
		String password= xl.getCellData("Login", i, 1);
		driver.findElement(By.name("txtUsername")).sendKeys(username);
		driver.findElement(By.name("txtPassword")).sendKeys(password);
		driver.findElement(By.name("Submit")).click();
		Thread.sleep(3000);
		String Expected ="dashboard";
		String Actual =driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			Reporter.log("Login Success::"+Expected+"    "+Actual,true);
			//write as login success into results cell
			xl.setCellData("Login", i, 2, "Login Success", outputpath);
			//write ass pass into status cell
			xl.setCellData("Login", i, 3, "Pass", outputpath);
			test.log(LogStatus.PASS, "Login Success"+Expected+"   "+Actual);
		}
		else
		{
		//take screen shot for fail test
			screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./Screen/Iteartion/"+i+"Login.png"));
			//capture error message
			String message= driver.findElement(By.id("spanMessage")).getText();
			Reporter.log(message+"   "+Expected+"    "+Actual,true);
			//write as message into results cell
			xl.setCellData("Login", i, 2, message, outputpath);
			//write ass Fail into status cell
			xl.setCellData("Login", i, 3, "Fail", outputpath);
			test.log(LogStatus.FAIL, message+"    "+Expected+"      "+Actual);
		}
		report.endTest(test);
		report.flush();
	}
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}










