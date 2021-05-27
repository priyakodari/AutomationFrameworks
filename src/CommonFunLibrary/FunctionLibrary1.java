package CommonFunLibrary;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import Constant.PBConstant1;


public class FunctionLibrary1 extends PBConstant1{
	//method login
		public static boolean verifyLogin(String username,String password)throws Throwable
		{
			driver.findElement(By.xpath(configprop.getProperty("ObjUsername"))).sendKeys(username);
			driver.findElement(By.xpath(configprop.getProperty("ObjPassword"))).sendKeys(password);
			driver.findElement(By.xpath(configprop.getProperty("ObjLogin"))).click();
			Thread.sleep(5000);
			String Expected = "adminflow";
			String Actual = driver.getCurrentUrl();
			if(Actual.toLowerCase().contains(Expected.toLowerCase()))
			{
				Reporter.log("Login Success:::"+Expected+"       "+Actual,true);
				return true;
			}
			else
			{
				Reporter.log("Login Fail:::"+Expected+"       "+Actual,true);
				return false;
			}
		}
		//method for click branches
		public static void clickBranches()throws Throwable
		{
			driver.findElement(By.xpath(configprop.getProperty("ObjBranches"))).click();
			Thread.sleep(5000);
			
		}
		//method for new branch creation
	public static boolean verifyNewBranch(String Bname,String Address1,String Address2,
		String Address3,String Area,String zipcode,String Country,String state,String city)throws Throwable	
	{
		driver.findElement(By.xpath(configprop.getProperty("ObjnewBranch"))).click();
	 Thread.sleep(3000);
	 driver.findElement(By.xpath(configprop.getProperty("ObjBranchName"))).sendKeys(Bname);
	 driver.findElement(By.xpath(configprop.getProperty("ObjAddress1"))).sendKeys(Address1);
	 driver.findElement(By.xpath(configprop.getProperty("ObjAddress2"))).sendKeys(Address2);
	 driver.findElement(By.xpath(configprop.getProperty("ObjAddress3"))).sendKeys(Address3);
	 driver.findElement(By.xpath(configprop.getProperty("ObjArea"))).sendKeys(Area);
	 driver.findElement(By.xpath(configprop.getProperty("Objzipcode"))).sendKeys(zipcode);
	 new Select(driver.findElement(By.xpath(configprop.getProperty("ObjCountry")))).selectByVisibleText(Country);
	 Thread.sleep(3000);
	 new Select(driver.findElement(By.xpath(configprop.getProperty("ObjState")))).selectByVisibleText(state);
	  Thread.sleep(3000);
	  new Select(driver.findElement(By.xpath(configprop.getProperty("ObjCity")))).selectByVisibleText(city);
	  Thread.sleep(3000);
	  driver.findElement(By.xpath(configprop.getProperty("ObjSubmit"))).click();
	  Thread.sleep(5000);
	  //capture alert message
	  String ActualAlert =driver.switchTo().alert().getText();
	  Thread.sleep(5000);
	  driver.switchTo().alert().accept();
	  Thread.sleep(5000);
	  String Expected ="new branch with";
	  if(ActualAlert.toLowerCase().contains(Expected.toLowerCase()))
	  {
		  Reporter.log("New Branch Created Success::"+Expected+"     "+ActualAlert,true);
		  return true;
	  }
	  else
	  {
		  Reporter.log("New Branch Fail to Create::"+Expected+"     "+ActualAlert,true);
		  return false;  
	  }
	}
	//method for branch updation
	public static boolean verifyBranchUpdate(String branchname,String Address1,String zipcode)
	throws Throwable{
	driver.findElement(By.xpath(configprop.getProperty("ObjEdit"))).click();
	Thread.sleep(5000);
	WebElement branch = driver.findElement(By.xpath(configprop.getProperty("ObjBranch")));
	branch.clear();
	branch.sendKeys(branchname);
	Thread.sleep(3000);
	WebElement add = driver.findElement(By.xpath(configprop.getProperty("ObjAddrerss")));
	add.clear();
	add.sendKeys(Address1);
	Thread.sleep(3000);
	WebElement zip = driver.findElement(By.xpath(configprop.getProperty("ObjZip")));
	zip.clear();
	zip.sendKeys(zipcode);
	Thread.sleep(3000);
	driver.findElement(By.xpath(configprop.getProperty("ObjUpdate"))).click();
	Thread.sleep(3000);
	//capture alert text
	String Expectedupdatealert=driver.switchTo().alert().getText();
	Thread.sleep(5000);
	driver.switchTo().alert().accept();
	Thread.sleep(3000);
	String ActualAlert="branch updated";
	if(Expectedupdatealert.toLowerCase().contains(ActualAlert.toLowerCase()))
	{
		Reporter.log("Branch Update Success::"+Expectedupdatealert+"    "+ActualAlert,true);
		return true;
	}
	else
	{
		Reporter.log("Branch Update Fail::"+Expectedupdatealert+"    "+ActualAlert,true);
		return false;	
	}
	}
	//method for logout
	public static boolean verifyLogout()throws Throwable
	{
		driver.findElement(By.xpath(configprop.getProperty("ObjLogout"))).click();
		Thread.sleep(4000);
		if(driver.findElement(By.xpath(configprop.getProperty("ObjLogin"))).isDisplayed())
		{
			Reporter.log("Logout Success",true);
			return true;
		}
		else
		{
			Reporter.log("Logout Fail",true);
			return false;
		}
	}
	//method for date generate
	public static String generateDate()
	{
		Date d = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy_MM_dd  hh_mm_ss");
		return sdf.format(d);
	}
	}










