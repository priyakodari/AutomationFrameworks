package PageLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddEmpPage {
	WebDriver driver;
	public  AddEmpPage(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(id="menu_pim_viewPimModule")
	WebElement clickPim;
	@FindBy(id="btnAdd")
	WebElement clickAddBtn;
	@FindBy(xpath="//input[@id='firstName']")
	WebElement EnterFname;
	@FindBy(xpath="//input[@id='middleName']")
	WebElement EnterMname;
	@FindBy(xpath="//input[@id='lastName']")
	WebElement EnterLname;
	@FindBy(xpath="//input[@id='btnSave']")
	WebElement ClickSaveBtn;
	public void verifyEmp(String FName, String MName, String LName) throws Throwable
	{
		Actions ac=new Actions(driver);
		ac.moveToElement(clickPim).click().perform();
		Thread.sleep(3000);
		ac.moveToElement(clickAddBtn).click().perform();
		Thread.sleep(3000);
		this.EnterFname.sendKeys(FName);
		this.EnterMname.sendKeys(MName);
		this.EnterLname.sendKeys(LName);
		ac.moveToElement(ClickSaveBtn).click().perform();
		Thread.sleep(3000);
		
	}
}
