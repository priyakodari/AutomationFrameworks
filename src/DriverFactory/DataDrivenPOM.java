package DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import PageLayer.AddUserPage;
import PageLayer.LoginPage;
import PageLayer.LogoutPage;
import Utilities.ExcelFileUtil;


public class DataDrivenPOM {
	WebDriver driver;
	ExtentReports report;
	//for log reporting this extenttest
	ExtentTest test;
	//defining 2 objects globally, i.e inputpath, output path
	String inputpath="D:\\Automation\\Automation_Frameworks\\TestInput\\UserCreation.xlsx";
	String outputpath="D:\\Automation\\Automation_Frameworks\\TestOutput\\userResults.xlsx";
	@BeforeTest
	public void setUp()throws Throwable
	{
		report= new ExtentReports("./ExtentReport/User.html");
		System.setProperty("webdriver.chrome.driver", "./CommonDrivers/chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("http://orangehrm.qedgetech.com/");
		driver.manage().window().maximize();
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		//call that login method of LoginPage which is stored in Login obj
		login.verifyLogin("Admin", "Qedge123!@#");
	}
	@Test
	public void adduser()throws Throwable
	{
		AddUserPage user = PageFactory.initElements(driver, AddUserPage.class);
		//acess excel methods
		//create one obj xl to access all excel methods 
		//excel path is input path
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count row in sheet
		//sheet name is userData
		int rc =xl.rowCount("userData");
		Reporter.log("No of rows::"+rc,true);
		//multiple data so loop
		for(int i=1;i<=rc; i++)
		{
			///u want to log the report for every iteration not for only one 
			test= report.startTest("User Creation");
			//to this userCreation functionality selenium has to pickup  the data from excel file, selenium has to read all columns
			//read all cells one by one so store them 
			String ename =xl.getCellData("userData", i, 0);
			String username =xl.getCellData("userData", i, 1);
			String password =xl.getCellData("userData", i, 2);
			String cpassword=xl.getCellData("userData", i, 3);
			//all these variable data should go into your userCreation form data, now userCreation method is stored into user
			//variable so call it
			user.verifyUser(ename, username, password, cpassword);
			String expected="viewSystemUsers";
			String actual =driver.getCurrentUrl();
			if(actual.contains(expected))
			{
				Reporter.log("User addess Success::"+expected+"      "+actual,true);
				//selenium should go and write into status column as pass or fail
				//Write the status as pass into outputpath file 
				xl.setCellData("userData", i, 4, "Pass", outputpath);
				test.log(LogStatus.PASS, "User addess Success::"+expected+"   "+actual);
			}
			else
			{
				Reporter.log("User Not added::"+expected+"      "+actual,true);
				xl.setCellData("userData", i, 4, "Fail", outputpath);
				test.log(LogStatus.FAIL, "User Not Added::"+expected+"   "+actual);
			}
			//For every Iteration pre condition and post condition both need to execute so end test and flush  
			report.endTest(test);
			report.flush();
		}
	}
	@AfterTest
	public void tearDown()throws Throwable
	{
		LogoutPage logout = PageFactory.initElements(driver, LogoutPage.class);
		logout.verifyLogout();
		driver.close();
	}
	}

//If we wont use excel methods we would have created Fileinput stream workbook one obj, sheet one obj 
//code reduced and time also


