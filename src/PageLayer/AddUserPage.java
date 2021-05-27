package PageLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddUserPage {
	WebDriver driver;
	public  AddUserPage(WebDriver driver)
	{
		this.driver=driver;
	}//Maintain Repository 
@FindBy(id="menu_admin_viewAdminModule")
WebElement clickAdmin;
@FindBy(id="menu_admin_UserManagement")
WebElement clickusermngnt;
@FindBy(id="menu_admin_viewSystemUsers")
WebElement clickUsers;
@FindBy(id="btnAdd")
WebElement clickAddbtn;
@FindBy(xpath="//input[@id='systemUser_employeeName_empName']")
WebElement EnterEname;
@FindBy(xpath="//input[@id='systemUser_userName']")
WebElement Enterusername;
@FindBy(xpath="//input[@id='systemUser_password']")
WebElement Enterpassword;
@FindBy(xpath="//input[@id='systemUser_confirmPassword'] ")
WebElement EnterCpassword;
@FindBy(xpath="//input[@id='btnSave']")
WebElement ClickSaveBtn;
public void verifyUser(String Ename, String UserName, String Password, String cpassword) throws Throwable 
{
Actions ac=new Actions(driver);
ac.moveToElement(clickAdmin).perform();
Thread.sleep(3000);
ac.moveToElement(clickusermngnt).perform();
Thread.sleep(3000);
ac.moveToElement(clickUsers).click().perform();
Thread.sleep(3000);
ac.moveToElement(clickAddbtn).click().perform();
Thread.sleep(3000);
this.EnterEname.sendKeys(Ename);
this.Enterusername.sendKeys(UserName);
this.Enterpassword.sendKeys(Password);
Thread.sleep(3000);
this.EnterCpassword.sendKeys(cpassword);
Thread.sleep(3000);
ac.moveToElement(ClickSaveBtn).click().perform();
Thread.sleep(3000);
}

}
