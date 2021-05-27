package PageLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
	@FindBy(id="welcome")
	WebElement ClickWelcome;
	@FindBy(linkText="Logout")
	WebElement clickLogout;
	
	public void verifyLogout() throws Throwable
	{
		ClickWelcome.click();
		Thread.sleep(3000);
		clickLogout.click();
		Thread.sleep(3000);
	}

}
