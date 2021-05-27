package PackageofDataProvider;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


public class ImplicitWaitCRM {
	WebDriver driver;
	@Test
	public void VerifyTest()
	{
		System.setProperty("webdriver.chrome.driver", "d:/Automation/chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://www.freecrm.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	Actions ac = new Actions(driver);
	driver.findElement(By.xpath("//body/div[1]/main[1]/section[1]/a[1]/span[1]")).click();
	driver.findElement(By.name("email")).sendKeys("pranga2010@gmail.com");
	driver.findElement(By.xpath("//input[@class='search']")).click();
	ac.moveToElement(driver.findElement(By.xpath("//input[@class='search']"))).sendKeys(Keys.ARROW_DOWN).perform();
	ac.sendKeys(Keys.ENTER).perform();
	}
	}



