package PageLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}//Maintain Repository 
	@FindBy(name="txtUsername")
	WebElement EnterUsername;
	@FindBy(name="txtPassword")
	WebElement EnterPassword;
	@FindBy(name="Submit")
	WebElement clickLogin;
public void verifyLogin(String userName, String password) throws Throwable 
{
	this.EnterUsername.sendKeys(userName);
	this.EnterPassword.sendKeys(password);
	this.clickLogin.click();

Thread.sleep(5000);
}
}
